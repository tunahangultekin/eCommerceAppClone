package com.example.ecommerceapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TutorialPagerAdapter(fragmentActivity: FragmentActivity, fragments: List<Fragment>) : FragmentStateAdapter(fragmentActivity) {
    private val fragments = listOf(
        ChooseProductsInfo(),
        MakePaymentInfo(),
        OrderInfo()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
