package com.fizahra.githubfii

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fizahra.githubfii.adapter.SectionsPagerAdapter
import com.fizahra.githubfii.database.FavUser
import com.fizahra.githubfii.databinding.ActivityDetailUserBinding
import com.fizahra.githubfii.helper.ViewModelFactory
import com.fizahra.githubfii.model.UserResponse
import com.fizahra.githubfii.viewmodel.DetailViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailUserBinding
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataUser = intent.getParcelableExtra<UserResponse.User>("username")
        val username = dataUser?.login
        val id = dataUser?.id
        val avatar = dataUser?.avatarUrl

        val user = FavUser()

        print("ini username ak : $username")
        Log.d(username,"ini username ak : $username")

        val detailViewModel = obtainViewModel(this@DetailUserActivity)
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

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        if (username != null) {
            detailViewModel.getFavByUsername(username).observe(this){ listFav ->
                isFavorite = listFav.isNotEmpty()
                if(listFav.isEmpty()){
                    binding.fabFav.changeIconColor(R.color.black)
                } else {
                    binding.fabFav.changeIconColor(R.color.red)
                }
            }
        }

        detailViewModel.toastMessage.observe(this) { toastMessage ->
            Toast.makeText(
                this@DetailUserActivity,
                toastMessage,
                Toast.LENGTH_SHORT
            ).show()
        }


        val sectionPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, username)
        binding.apply{
            vpFollow.adapter = sectionPagerAdapter
            tabFollow.setupWithViewPager(vpFollow)
            fabFav.setOnClickListener {
                if(isFavorite){
                    if (id != null) {
                        detailViewModel.unFav(id)
                    }
                    Toast.makeText(
                        this@DetailUserActivity,
                        "Remove from favorite",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (username != null && avatar != null && id != null) {
                        detailViewModel.addToFav(username, avatar, id)
                    }
                    Toast.makeText(
                        this@DetailUserActivity,
                        "Add to your favorite",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbDet.visibility = View.VISIBLE
        } else {
            binding.pbDet.visibility = View.GONE
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DetailViewModel::class.java)
    }

    fun FloatingActionButton.changeIconColor(@ColorRes color:Int){
        imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this.context, color))
    }
}