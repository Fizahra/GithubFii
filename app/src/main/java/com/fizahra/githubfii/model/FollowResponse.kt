package com.fizahra.githubfii.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class FollowResponse : ArrayList<FollowResponse.FollowResponseItem>(){
    @Parcelize
    data class FollowResponseItem(
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("login")
        val login: String
    ): Parcelable
}