package com.fizahra.githubfii.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fizahra.githubfii.database.FavUser
import com.fizahra.githubfii.repository.FavUserRepository

class FavViewModel(application : Application) : ViewModel() {
    private val mFavUserRepository : FavUserRepository = FavUserRepository(application)

    fun getAllFav(): LiveData<List<FavUser>> = mFavUserRepository.getAllFavUser()
}