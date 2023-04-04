package com.fizahra.githubfii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.fizahra.githubfii.adapter.FavUserAdapter
import com.fizahra.githubfii.databinding.ActivityFavoriteUserBinding
import com.fizahra.githubfii.helper.ViewModelFactory
import com.fizahra.githubfii.viewmodel.DetailViewModel

class FavoriteUserActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteUserBinding
    private lateinit var adapter : FavUserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favViewModel = obtainViewModel(this@FavoriteUserActivity)

    }


    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DetailViewModel::class.java)
    }
}