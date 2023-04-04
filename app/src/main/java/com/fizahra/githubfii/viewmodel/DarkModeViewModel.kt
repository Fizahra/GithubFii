package com.fizahra.githubfii.viewmodel

import androidx.lifecycle.*
import com.fizahra.githubfii.helper.DarkModePreference
import kotlinx.coroutines.launch

class DarkModeViewModel(private val pref: DarkModePreference) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }

    class Factory(private val pref : DarkModePreference): ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel> create(modelClass: Class<T>): T = DarkModeViewModel(pref) as T
    }
}