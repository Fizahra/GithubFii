package com.fizahra.githubfii.service

import com.fizahra.githubfii.model.FollowResponse
import com.fizahra.githubfii.model.UserDetailResponse
import com.fizahra.githubfii.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") username : String
    ): Call<UserDetailResponse>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username : String
    ): Call<FollowResponse>

    @GET("users/{username}/fpathollowing")
    fun getFollowing(
        @Path("username") username : String
    ): Call<FollowResponse>
}