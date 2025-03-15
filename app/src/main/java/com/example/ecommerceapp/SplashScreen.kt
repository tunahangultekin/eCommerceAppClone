package com.example.ecommerceapp

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SplashScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Read SharedPreferences to check if it's the first launch
        val sharedPref = requireContext().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        var isFirstLaunch = sharedPref.getBoolean("isFirstLaunch", true) // Default value is 'true'


        isFirstLaunch=true
        Log.d("SplashScreen", "isFirstLaunch value: $isFirstLaunch")

        // Wait 3 seconds, then navigate based on 'isFirstLaunch'
        Handler(Looper.getMainLooper()).postDelayed({
            if (isFirstLaunch) {
                findNavController().navigate(R.id.action_splashScreen_to_onboardingPage)
            } else {
                // Navigate to another screen, e.g., the home screen
                findNavController().navigate(R.id.action_onboardingPage_to_signUp)
            }
        }, 3000) // Wait for 3 seconds
    }
}
