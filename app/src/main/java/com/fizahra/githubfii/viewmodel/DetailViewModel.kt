package com.fizahra.githubfii.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fizahra.githubfii.config.ApiConfig
import com.fizahra.githubfii.model.UserDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _listdetail = MutableLiveData<UserDetailResponse>()
    val listdetail : LiveData<UserDetailResponse> = _listdetail

    companion object{
        private  const val TAG = "DetailViewModel"
    }

    fun getDetail(username : String){
        val client = ApiConfig.getApiService().getUserDetail(username)
        client.enqueue(object : Callback<UserDetailResponse>{
            override fun onResponse(
                call: Call<UserDetailResponse>,
                response: Response<UserDetailResponse>
            ) {
                _listdetail.postValue(response.body())
            }

            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message}")

        }
    })
    }
}