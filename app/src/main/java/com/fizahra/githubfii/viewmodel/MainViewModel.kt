package com.fizahra.githubfii.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fizahra.githubfii.config.ApiConfig
import com.fizahra.githubfii.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _listUser = MutableLiveData<ArrayList<UserResponse.User>>()
    val listUser : LiveData<ArrayList<UserResponse.User>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage : LiveData<String> = _toastMessage

    companion object{
        private  const val TAG = "MainViewModel"
    }

    fun setSearchUser(query: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getSearchUser(query)
        client.enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val responseBody = response.body()?.items
                    if (responseBody != null && responseBody.size > 0){
                        _listUser.value = responseBody
                    }else{
                        _toastMessage.value = "User not found"
                    }
                }
                Log.d(TAG, "OnFailure : ${response.message()}")
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure : ${t.message}")
            }

        })
    }
}