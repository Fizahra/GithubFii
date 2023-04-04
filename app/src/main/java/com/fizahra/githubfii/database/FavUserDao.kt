package com.fizahra.githubfii.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(fav : FavUser)

    @Query("SELECT * FROM user")
    fun getAllFav(): LiveData<List<FavUser>>

    @Query("SELECT * FROM user WHERE username = :username")
    fun findFavByUsername(username : String): LiveData<List<FavUser>>

    @Query("DELETE FROM user WHERE username = :username")
    fun delete(username : String)
}