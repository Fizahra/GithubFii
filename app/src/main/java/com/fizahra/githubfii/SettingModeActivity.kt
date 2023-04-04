package com.fizahra.githubfii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.fizahra.githubfii.databinding.ActivitySettingModeBinding
import com.fizahra.githubfii.helper.DarkModePreference
import com.fizahra.githubfii.viewmodel.DarkModeViewModel

class SettingModeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingModeBinding
    private val darkViewModel by viewModels<DarkModeViewModel>{
        DarkModeViewModel.Factory(DarkModePreference(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        darkViewModel.getThemeSettings().observe(this) {
            if (it) {
                binding.switchTheme.text = "Dark Mode"
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                binding.switchTheme.text = "Day Mode"
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            binding.switchTheme.isChecked = it
        }
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            darkViewModel.saveThemeSetting(isChecked)
        }

    }
}