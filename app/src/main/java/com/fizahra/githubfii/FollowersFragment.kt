package com.fizahra.githubfii

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fizahra.githubfii.adapter.FollowersAdapter
import com.fizahra.githubfii.adapter.FollowingAdapter
import com.fizahra.githubfii.databinding.FragmentFollowBinding
import com.fizahra.githubfii.viewmodel.FollowersViewModel
import com.fizahra.githubfii.viewmodel.FollowingViewModel


class FollowersFragment : Fragment() {

    companion object{
        const val SECTION_NUMBER = "section_number"
        const val EXTRA_USERNAME = "extra_username"
    }

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var followerViewModel: FollowersViewModel
    private lateinit var followerViewModels: FollowingViewModel
    private lateinit var adapter: FollowersAdapter
    private lateinit var adapters: FollowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val index = arguments?.getInt(SECTION_NUMBER, 0)
        val username = arguments?.getString(EXTRA_USERNAME)?: ""
        print("ini username ak : $username")
        Log.d(username, "ini username ak : $username")

        adapter = FollowersAdapter()
        adapters = FollowingAdapter()

        if(index == 0){
            binding.apply {
                rvFollow.setHasFixedSize(true)
                rvFollow.layoutManager = LinearLayoutManager(activity)
                rvFollow.adapter = adapter
            }
            followerViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(FollowersViewModel::class.java)
            followerViewModel.getFollowers(username)
            followerViewModel.listFollowers.observe(viewLifecycleOwner) {
                if (it != null) {
                    adapter.setList(it)
                }
            }
            followerViewModel.isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
        } else {
            binding.apply {
                rvFollow.setHasFixedSize(true)
                rvFollow.layoutManager = LinearLayoutManager(activity)
                rvFollow.adapter = adapter
            }
            followerViewModels = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(FollowingViewModel::class.java)
            followerViewModels.getFollowing(username)
            followerViewModels.listFollowing.observe(viewLifecycleOwner) {
                if (it != null) {
                    adapter.setList(it)
                }
            }
            followerViewModels.isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
        }

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbFollow.visibility = View.VISIBLE
        } else {
            binding.pbFollow.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}