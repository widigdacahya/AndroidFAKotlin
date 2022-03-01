package com.example.boxvolumecounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.boxvolumecounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

//    private lateinit var edtWidth : EditText
//    private lateinit var edtLength : EditText
//    private lateinit var edtHeight : EditText
//    private lateinit var btnCalc : Button
//    private lateinit var tvResult : TextView

    private lateinit var binding: ActivityMainBinding


    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //edtWidth = findViewById(R.id.etWidthUI)
        //edtLength = findViewById(R.id.etLengthUI)
        //edtHeight = findViewById(R.id.etHeightUI)
        //btnCalc = findViewById(R.id.btnCalculateVolumeUI)
        //tvResult = findViewById(R.id.tvResultUI)


        //btnCalc.setOnClickListener (this)
        binding.btnCalculateVolumeUI.setOnClickListener(this)

        if(savedInstanceState != null) {
            val theResult = savedInstanceState.getString(STATE_RESULT)
            //tvResult.text = theResult
            binding.tvResultUI.text = theResult
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //outState.putString(STATE_RESULT, tvResult.text.toString())
        outState.putString(STATE_RESULT,binding.tvResultUI.text.toString())
    }

//    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
//        super.onSaveInstanceState(outState, outPersistentState)
//        outState.putString(STATE_RESULT, tvResult.text.toString())
//    }


    override fun onClick(v: View?) {

        if (v?.id == R.id.btnCalculateVolumeUI) {
            //val textLenght = edtLength.text.toString().trim()
            //val textHeight = edtHeight.text.toString().trim()
            //val textWidth = edtWidth.text.toString().trim()
            val textLenght = binding.etLengthUI.text.toString().trim()
            val textHeight = binding.etHeightUI.text.toString().trim()
            val textWidth = binding.etWidthUI.text.toString().trim()


            var isEmptyFields = false

            if(textHeight.isEmpty()) {
                isEmptyFields = true
                binding.etHeightUI.error = "Height must not be empty"
            }
            if(textLenght.isEmpty()) {
                isEmptyFields = true
                binding.etLengthUI.error = "Length must not be empty"
            }
            if(textWidth.isEmpty()) {
                isEmptyFields = true
                binding.etWidthUI.error = "Width must not be empty"
            }

            if(!isEmptyFields) {
                val theVolume = textLenght.toDouble()*textHeight.toDouble()*textWidth.toDouble()
                binding.tvResultUI.text = theVolume.toString()
            }


        }

    }


}