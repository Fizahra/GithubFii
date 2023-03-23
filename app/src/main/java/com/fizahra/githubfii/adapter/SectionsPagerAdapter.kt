package com.fizahra.githubfii.adapter

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fizahra.githubfii.FollowersFragment
import com.fizahra.githubfii.R

class SectionsPagerAdapter(private val ctx: Context, fragmanager: FragmentManager, private val username: String?) : FragmentPagerAdapter(fragmanager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val tabTitle = intArrayOf(R.string.followers, R.string.following)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment = FollowersFragment()
        fragment?.arguments = Bundle().apply{
            putInt(FollowersFragment.SECTION_NUMBER, position)
            putString(FollowersFragment.EXTRA_USERNAME, username)
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return ctx.resources.getString(tabTitle[position])
    }
}