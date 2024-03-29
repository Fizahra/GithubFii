package com.fizahra.githubfii

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.fizahra.githubfii.helper.DarkModePreference
import com.fizahra.githubfii.viewmodel.DarkModeViewModel

@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {

    private val darkViewModel by viewModels<DarkModeViewModel>{
        DarkModeViewModel.Factory(DarkModePreference(this))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler(Looper.getMainLooper()).postDelayed({
            val intentHome = Intent(this, MainActivity::class.java)
            startActivity(intentHome)
        }, 4000)

        darkViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}