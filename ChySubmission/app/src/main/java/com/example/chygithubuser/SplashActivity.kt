package com.example.chygithubuser

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chygithubuser.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)


        splashBinding.root.alpha = 0f
        splashBinding.root.animate().setDuration(2000).alpha(1f).withEndAction {
            val intentToMain = Intent(this,MainActivity::class.java)
            startActivity(intentToMain)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }

    }
}