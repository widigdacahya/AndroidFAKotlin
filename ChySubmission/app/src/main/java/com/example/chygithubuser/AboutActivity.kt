package com.example.chygithubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chygithubuser.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var aboutBinding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aboutBinding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(aboutBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}