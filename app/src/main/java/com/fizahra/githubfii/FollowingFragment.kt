package com.fizahra.githubfii

import android.os.Bundle
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


class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var followingViewModel: FollowingViewModel
    private lateinit var adapter: FollowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        adapter = FollowingAdapter()

        binding.apply {
            rvFollow.setHasFixedSize(true)
            rvFollow.layoutManager = LinearLayoutManager(activity)
            rvFollow.adapter = adapter
        }

        followingViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowingViewModel::class.java)
        followingViewModel.getFollowing(username)
        followingViewModel.listFollowing.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setList(it)
            }
        }

        followingViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
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