package com.fizahra.githubfii.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavUser::class], version = 1, exportSchema = false)
abstract class FavUserRoomDatabase : RoomDatabase() {
    abstract fun favUserDao() : FavUserDao

    companion object{
        @Volatile
        private var INSTANCE : FavUserRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context : Context): FavUserRoomDatabase{
            if(INSTANCE == null){
                synchronized(FavUserRoomDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavUserRoomDatabase::class.java, "userdb")
                        .build()
                }
            }
            return INSTANCE as FavUserRoomDatabase
        }
    }
}