package com.example.ourintentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MoveWithData : AppCompatActivity() {

    companion object {
        const val EXTRA_AGE = "extra_age"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PICT_ID = "extra_pict_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_data)

        val tvDataReceiver: TextView = findViewById(R.id.tvMoveWithDataUI)
        val theImageReceiver : ImageView = findViewById(R.id.ivMoveWithDataUI)

        val name = intent.getStringExtra(EXTRA_NAME)
        val age = intent.getIntExtra(EXTRA_AGE,0)
        val pictId = intent.getIntExtra(EXTRA_PICT_ID,0)

        val theMoveDatText = "Hi I am $name, my age is $age y.o!"
        tvDataReceiver.text = theMoveDatText

        theImageReceiver.setImageResource(pictId)


    }
}