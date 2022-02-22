package com.example.ourintentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MoveWithObjectActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        val tvChangedWithObj: TextView = findViewById(R.id.tvReceiveWithObjectUI)

        val person = intent.getParcelableExtra<Person>(EXTRA_PERSON) as Person
        val textPerson = "\uD83D\uDC4B Hi, I am ${person.name.toString()}, my age is ${person.age}.\nYou can reach me from ${person.email}. I live in ${person.city} \uD83C\uDF03"
        tvChangedWithObj.text = textPerson

    }
}