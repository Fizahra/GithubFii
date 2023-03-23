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
import com.fizahra.githubfii.viewmodel.FollowViewModel


class FollowersFragment : Fragment() {

    companion object{
        const val SECTION_NUMBER = "section_number"
        const val EXTRA_USERNAME = "extra_username"
    }

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterFollower: FollowersAdapter
    private lateinit var adapterFollowing: FollowingAdapter
    private lateinit var followViewModel : FollowViewModel

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

        adapterFollower = FollowersAdapter()
        adapterFollowing = FollowingAdapter()
        binding.apply{
            rvFollow.setHasFixedSize(true)
            rvFollow.layoutManager = LinearLayoutManager(activity)
        }

        if(index == 0){
            binding.rvFollow.adapter = adapterFollower
            followViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(FollowViewModel::class.java)
            followViewModel.getFollowers(username)
            followViewModel.listFollowers.observe(viewLifecycleOwner) {
                if (it != null && it.size > 0) {
                    binding.tvNotice.visibility = View.GONE
                    adapterFollower.setList(it)
                }
            }
            followViewModel.isLoading.observe(viewLifecycleOwner) {
                showLoading(it)
            }
        } else{
            binding.rvFollow.adapter = adapterFollowing
            followViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(FollowViewModel::class.java)
            followViewModel.getFollowing(username)
            followViewModel.listFollowing.observe(viewLifecycleOwner) {
                if (it != null && it.size > 0) {
                    binding.tvNotice.visibility = View.GONE
                    adapterFollowing.setList(it)
                }
            }
            followViewModel.isLoading.observe(viewLifecycleOwner) {
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