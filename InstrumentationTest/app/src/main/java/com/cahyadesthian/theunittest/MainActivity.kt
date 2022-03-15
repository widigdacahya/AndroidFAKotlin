package com.cahyadesthian.theunittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.cahyadesthian.theunittest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        mainViewModel = MainViewModel(CuboidModel())

        activityMainBinding.btnSave.setOnClickListener(this)
        activityMainBinding.btnCalcArea.setOnClickListener(this)
        activityMainBinding.btnCalcCircumference.setOnClickListener(this)
        activityMainBinding.btnCalcVolume.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        val length = activityMainBinding.edtLength.text.toString().trim()
        val width = activityMainBinding.edtWidth.text.toString().trim()
        val height = activityMainBinding.edtHeight.text.toString().trim()

        when {

            TextUtils.isEmpty(length) -> activityMainBinding.edtLength.error = "This Field Length Can't be empty"
            TextUtils.isEmpty(width) -> activityMainBinding.edtWidth.error = "This Field Width Can't be empty"
            TextUtils.isEmpty(height) -> activityMainBinding.edtHeight.error = "This Field Height Can't be empty"
            else -> {

                val valLength = length.toDouble()
                val valWidth = width.toDouble()
                val valHeight = height.toDouble()

                when(v.id) {
                    R.id.btn_save -> {
                        mainViewModel.save(valLength,valWidth,valHeight)
                        visible()
                    }
                    R.id.btn_calc_area -> {
                        activityMainBinding.tvResult.text = mainViewModel.getSurfaceArea().toString()
                        gone()
                    }
                    R.id.btn_calc_circumference -> {
                        activityMainBinding.tvResult.text = mainViewModel.getCircumference().toString()
                        gone()
                    }
                    R.id.btn_calc_volume -> {
                        activityMainBinding.tvResult.text = mainViewModel.getVolume().toString()
                        gone()
                    }
                }

            }
        }

    }

    private fun visible() {
        activityMainBinding.apply {
            btnSave.visibility = View.GONE
            btnCalcArea.visibility = View.VISIBLE
            btnCalcCircumference.visibility = View.VISIBLE
            btnCalcVolume.visibility = View.VISIBLE
        }
    }

    private fun gone() {
        activityMainBinding.apply {
            btnSave.visibility = View.VISIBLE
            btnCalcArea.visibility = View.GONE
            btnCalcCircumference.visibility = View.GONE
            btnCalcVolume.visibility = View.GONE
        }
    }

}