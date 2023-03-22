package com.fizahra.githubfii.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fizahra.githubfii.config.ApiConfig
import com.fizahra.githubfii.model.FollowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {
    private val _listFollowers = MutableLiveData<ArrayList<FollowResponse.FollowResponseItem>>()
    val listFollowers : LiveData<ArrayList<FollowResponse.FollowResponseItem>> = _listFollowers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun getFollowers(username : String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<FollowResponse>{
            override fun onResponse(
                call: Call<FollowResponse>,
                response: Response<FollowResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _listFollowers.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<FollowResponse>, t: Throwable) {
                _isLoading.value = false
                t.message?.let { Log.d("onFailure : ", it) }
            }

        })
    }
}