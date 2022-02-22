package com.example.ourintentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var tvResult: TextView

    private var resultLauncher:ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
            if(result.resultCode == MoveForResultActivity.RESULT_CODE && result.data!=null) {
                val selectedValue = result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE,0)
                tvResult.text = "Hasil : $selectedValue"
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btnMoveActivityUI)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveWithData: Button = findViewById(R.id.btnMoveActivityWithDataUI)
        btnMoveWithData.setOnClickListener(this)

        val btnMoveWithObject : Button = findViewById(R.id.btnMoveActivityWithObjectUI)
        btnMoveWithObject.setOnClickListener(this)

        val btnDialNumber: Button = findViewById(R.id.btnMoveToDialUI)
        btnDialNumber.setOnClickListener(this)

        val btnMoveResult: Button = findViewById(R.id.btnMoveWithResultUI)
        btnMoveResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tvResultFromActivityUI)

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
            R.id.btnMoveToDialUI -> {
                val thePhoneNumber = "085746641537"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$thePhoneNumber"))
                startActivity(dialPhoneIntent)
            }
            R.id.btnMoveWithResultUI -> {
                val moveWithResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                resultLauncher.launch(moveWithResultIntent)
            }
        }
    }


}