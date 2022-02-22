package com.example.ourintentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup

class MoveForResultActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnChoose : Button
    private lateinit var rgNumber : RadioGroup


    companion object {
        const val EXTRA_SELECTED_VALUE = "extra_selected_value"
        const val RESULT_CODE =  90
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_for_result)

        btnChoose = findViewById(R.id.btnChooseNumUI)
        rgNumber = findViewById(R.id.rgNumberUI)


        btnChoose.setOnClickListener(this)

    }

    override fun onClick(v: View) {

        if(v.id == R.id.btnChooseNumUI) {

            if(rgNumber.checkedRadioButtonId>0) {

                var value = 0
                when(rgNumber.checkedRadioButtonId) {
                    R.id.rb50UI -> value = 50
                    R.id.rb90UI -> value = 90
                    R.id.rb120UI -> value = 120
                    R.id.rb150UI -> value = 150
                }

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_SELECTED_VALUE,value)
                setResult(RESULT_CODE,resultIntent)
                finish()
            }

        }

    }


}