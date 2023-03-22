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

    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var binding : ActivityDetailUserBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataUser = intent.getParcelableExtra<UserResponse.User>("username")
        val username = dataUser?.login

        if(uname != null){
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

        val uname = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, uname)

        val sectionPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply{
            vpFollow.adapter = sectionPagerAdapter
            tabFollow.setupWithViewPager(vpFollow)
        }
    }
}