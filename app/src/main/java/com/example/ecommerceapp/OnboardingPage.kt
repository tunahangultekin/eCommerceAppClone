package com.example.ecommerceapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)
        val tabIndicator: TabLayout = view.findViewById(R.id.tabIndicator)
        val nextButton: Button = view.findViewById(R.id.btnNext)

        // List of fragments for onboarding
        val fragments = listOf(
            ChooseProductsInfo(),
            MakePaymentInfo(),
            OrderInfo()
        )

        val adapter = TutorialPagerAdapter(requireActivity(), fragments)
        viewPager.adapter = adapter

        // Attach TabLayout with ViewPager2
        TabLayoutMediator(tabIndicator, viewPager) { tab, position ->
            tab.text = "${position + 1}" // Assign meaningful text for each tab
        }.attach()

        // Next Button Click Listener
        nextButton.setOnClickListener {
            if (viewPager.currentItem < fragments.size - 1) {
                // Move to the next page in the ViewPager2
                viewPager.currentItem += 1
            } else {
                // Handle the completion of onboarding
                finishOnboarding()
            }
        }
    }

    private fun finishOnboarding() {
        // Mark onboarding as complete in SharedPreferences
        val sharedPref = requireContext().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        sharedPref.edit().putBoolean("isFirstLaunch", false).apply()

        // Navigate to the main screen or home page
       findNavController().navigate(R.id.action_onboardingPage_to_baseFragment)
    }
}
