package com.sisada.bookstore

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm,lifecycle) {
    val pageNumber = 3

    override fun getItemCount(): Int {
        return pageNumber
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FragmentOne()
            1 -> FragmentTwo()
            2 -> FragmentThree()
            else -> FragmentOne()
        }
    }


}