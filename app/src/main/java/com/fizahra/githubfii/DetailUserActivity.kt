package com.fizahra.githubfii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.fizahra.githubfii.adapter.SectionsPagerAdapter
import com.fizahra.githubfii.databinding.ActivityDetailUserBinding
import com.fizahra.githubfii.model.UserResponse
import com.fizahra.githubfii.viewmodel.DetailViewModel

class DetailUserActivity : AppCompatActivity() {


    private lateinit var binding : ActivityDetailUserBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataUser = intent.getParcelableExtra<UserResponse.User>("username")
        val username = dataUser?.login
        print("ini username ak : $username")

        if(username != null){
            detailViewModel.getDetail(username)
        }

        detailViewModel.listdetail.observe(this) {
            if (it != null) {
                binding.apply {
                    tvUsername.text = it.login
                    tvName.text = it.name
                    tvBio.text = it.bio
                    tvFollower.text = "${it.followers} ${resources.getString(R.string.followers)}"
                    tvFollowing.text = "${it.following} ${resources.getString(R.string.following)}"
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatarUrl)
                        .into(imgUser)
                }
            }
        }

        val sectionPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, username)
        binding.apply{
            vpFollow.adapter = sectionPagerAdapter
            tabFollow.setupWithViewPager(vpFollow)
        }
    }
}