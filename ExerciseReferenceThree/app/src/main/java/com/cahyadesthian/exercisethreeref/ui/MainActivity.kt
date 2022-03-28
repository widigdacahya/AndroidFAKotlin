package com.cahyadesthian.exercisethreeref.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cahyadesthian.exercisethreeref.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
    }
}