package com.fizahra.githubfii.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.fizahra.githubfii.database.FavUser
import com.fizahra.githubfii.database.FavUserDao
import com.fizahra.githubfii.database.FavUserRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavUserRepository(application : Application) {
    private val mFavUserDao: FavUserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init{
        val db = FavUserRoomDatabase.getDatabase(application)
        mFavUserDao = db.favUserDao()
    }

    fun getAllFavUser(): LiveData<List<FavUser>> = mFavUserDao.getAllFav()

    fun getFavByUsername(username: String): LiveData<List<FavUser>> = mFavUserDao.findFavByUsername(username)

    fun insert(user: FavUser){
        executorService.execute { mFavUserDao.insert(user) }
    }

    fun delete(id: Int){
        executorService.execute { mFavUserDao.delete(id) }
    }

}