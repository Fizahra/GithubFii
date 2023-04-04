package com.fizahra.githubfii.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName= "user")
@Parcelize
data class FavUser (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = 0,

    @ColumnInfo(name = "username")
    val username: String? = "",

    @ColumnInfo(name = "avatar")
    val avatar_url: String? = ""

): Parcelable