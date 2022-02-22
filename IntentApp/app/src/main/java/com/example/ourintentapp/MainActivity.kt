package com.example.ourintentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btnMoveActivityUI)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveWithData: Button = findViewById(R.id.btnMoveActivityWithDataUI)
        btnMoveWithData.setOnClickListener(this)

        val btnMoveWithObject : Button = findViewById(R.id.btnMoveActivityWithObjectUI)
        btnMoveWithObject.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnMoveActivityUI -> {
                val moveIntentExplicit = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntentExplicit)
            }
            R.id.btnMoveActivityWithDataUI -> {
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithData::class.java)
                moveWithDataIntent.putExtra(MoveWithData.EXTRA_PICT_ID, R.drawable.kitten)
                moveWithDataIntent.putExtra(MoveWithData.EXTRA_NAME, "Pupppy")
                moveWithDataIntent.putExtra(MoveWithData.EXTRA_AGE,1)
                startActivity(moveWithDataIntent)
            }
            R.id.btnMoveActivityWithObjectUI -> {
                val thePerson = Person(
                    "Cahyaningrum Agniasari Permata",
                    22,
                    "chyaningrum@gmail.com",
                    "Bondowoso"
                )


                val moveWithObjIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObjIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, thePerson)
                startActivity(moveWithObjIntent)
            }
        }
    }


}