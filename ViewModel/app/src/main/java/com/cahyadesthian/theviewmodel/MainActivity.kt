package com.cahyadesthian.theviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.cahyadesthian.theviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        //connect mainActivity with viewModel Class :*
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        displayResult()

        btnCalculateClicked()


    }

    private fun btnCalculateClicked() {
        mainBinding.btnCalculateUI.setOnClickListener {

            val widthData = mainBinding.etWidthUI.text
            val heightData = mainBinding.etHeightUI.text
            val lengthData = mainBinding.etLengthUI.text

            when {
                widthData.isEmpty() -> mainBinding.etWidthUI.error = "Please fill it"
                heightData.isEmpty()-> mainBinding.etHeightUI.error = "Please fill it"
                lengthData.isEmpty() -> mainBinding.etLengthUI.error = "Please fill it"
                else -> {
                    viewModel.calculate(widthData.toString().toDouble(), heightData.toString().toDouble(), lengthData.toString().toDouble())
                    displayResult()
                }
            }

        }


    }


    private fun displayResult() {
        mainBinding.tvResultUI.text = viewModel.result.toString()
    }


}