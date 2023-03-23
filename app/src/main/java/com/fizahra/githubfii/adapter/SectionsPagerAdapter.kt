package com.fizahra.githubfii.adapter

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fizahra.githubfii.FollowersFragment
import com.fizahra.githubfii.FollowingFragment
import com.fizahra.githubfii.R

class SectionsPagerAdapter(private val ctx: Context, fragmanager: FragmentManager, data: String?) : FragmentPagerAdapter(fragmanager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragBundle : String?

    @StringRes
    private val tabTitle = intArrayOf(R.string.followers, R.string.following)

    init {
        fragBundle = data
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        fragment?.arguments = Bundle().apply{
            putString(FollowersFragment.EXTRA_USERNAME, fragBundle)
            putString(FollowingFragment.EXTRA_USERNAME, fragBundle)
        }
        when(position){
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return ctx.resources.getString(tabTitle[position])
    }
}