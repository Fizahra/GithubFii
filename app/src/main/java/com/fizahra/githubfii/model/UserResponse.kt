package com.fizahra.githubfii.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class UserResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: ArrayList<User>,
    @SerializedName("total_count")
    val totalCount: Int
) {
    @Parcelize
    data class User(
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("login")
        val login: String
    ): Parcelable
}