package com.fizahra.githubfii.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fizahra.githubfii.config.ApiConfig
import com.fizahra.githubfii.database.FavUser
import com.fizahra.githubfii.database.FavUserDao
import com.fizahra.githubfii.database.FavUserRoomDatabase
import com.fizahra.githubfii.model.UserDetailResponse
import com.fizahra.githubfii.repository.FavUserRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : ViewModel() {
    private val mFavUserRepository : FavUserRepository = FavUserRepository(application)

    private val _listdetail = MutableLiveData<UserDetailResponse>()
    val listdetail : LiveData<UserDetailResponse> = _listdetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading
    var isFav = false
     val resultAddFav = MutableLiveData<Boolean>()
     val resultUnFav = MutableLiveData<Boolean>()
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage : LiveData<String> = _toastMessage


    companion object{
        private  const val TAG = "DetailViewModel"
    }

    fun getDetail(username : String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserDetail(username)
        client.enqueue(object : Callback<UserDetailResponse>{
            override fun onResponse(
                call: Call<UserDetailResponse>,
                response: Response<UserDetailResponse>
            ) {
                _isLoading.value = false
                _listdetail.postValue(response.body())
            }

            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure : ${t.message}")

        }
    })
    }

//    fun setFav(username: String, avatar: String, id: Int) {
//        var user = FavUser(
//            id,
//            avatar,
//            username,
//        )
//        user.let { viewModelScope.launch {
//            if (isFav) {
//                unFav(id)
//                resultUnFav.value =true
//                _toastMessage.value = "Remove from favorite"
//            }else{
//                addToFav(username, avatar, id)
//                resultAddFav.value =true
//                _toastMessage.value = "Add to favorite"
//            }
//
//        }
//            isFav = !isFav
//        }


//    }
    fun addToFav(username: String, avatar: String, id: Int){
        viewModelScope.launch {
            val user = FavUser(
                id,
                avatar,
                username,
            )
            mFavUserRepository.insert(user)
        }
    }
//    fun addToFav(user: FavUser){
//        mFavUserRepository.insert(user)
//    }

    fun unFav(id: Int){
            mFavUserRepository.delete(id)
    }

    fun getFavByUsername(username: String): LiveData<List<FavUser>>{
        return mFavUserRepository.getFavByUsername(username)
    }
}