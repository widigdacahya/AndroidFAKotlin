package com.cahyadesthian.getridviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cahyadesthian.getridviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mainBinding: ActivityMainBinding

    lateinit var viewModel: TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        viewModel = ViewModelProvider(this).get(TestViewModel::class.java)
        mainBinding.btnIncrement.setOnClickListener(this)

        viewModel.currentNumber.observe(this, Observer {
            mainBinding.tvCountData.text = it.toString()
        })

        viewModel.currentBoolean.observe(this, Observer {
            mainBinding.tvBooleanData.text = it.toString()
        })

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_increment -> {
                viewModel.currentNumber.value = ++viewModel.number
                viewModel.currentBoolean.value = viewModel.number %2 == 0
            }
        }
    }


}