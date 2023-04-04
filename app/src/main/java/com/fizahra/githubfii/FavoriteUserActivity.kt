package com.fizahra.githubfii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fizahra.githubfii.adapter.UserAdapter
import com.fizahra.githubfii.database.FavUser
import com.fizahra.githubfii.databinding.ActivityFavoriteUserBinding
import com.fizahra.githubfii.helper.ViewModelFactory
import com.fizahra.githubfii.model.UserResponse
import com.fizahra.githubfii.viewmodel.FavViewModel

class FavoriteUserActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favViewModel = obtainViewModel(this@FavoriteUserActivity)

        favViewModel.getAllFav().observe(this){users: List<FavUser> ->
            val items = arrayListOf<UserResponse.User>()
            users.map{
                val item =
                    it.avatar_url?.let { it1 -> UserResponse.User(login = it.username, avatarUrl = it1, id = it.id) }
                if (item != null) {
                    binding.tvNothing.visibility = View.GONE
                    items.add(item)
                }
            }
            binding.rvFav.adapter = UserAdapter(items)
        }
        binding.rvFav.layoutManager = LinearLayoutManager(this)
        binding.rvFav.setHasFixedSize(true)

    }

    private fun obtainViewModel(activity: AppCompatActivity): FavViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavViewModel::class.java)
    }

}