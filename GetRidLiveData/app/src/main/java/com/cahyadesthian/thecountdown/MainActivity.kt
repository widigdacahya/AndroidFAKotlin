package com.cahyadesthian.thecountdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cahyadesthian.thecountdown.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainBinding: ActivityMainBinding

    //Caused by: java.lang.IllegalStateException: Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.
    //private val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        (viewModel as MainActivityViewModel).seconds.observe(this, Observer {
            mainBinding.tvCountdown.text = it.toString()
        })
        (viewModel as MainActivityViewModel).finished.observe(this, Observer {
            if(it) Toast.makeText(this, "Time's Up ⏰", Toast.LENGTH_SHORT).show()
        })

        mainBinding.apply {
            btnStart.setOnClickListener(this@MainActivity)
            btnStop.setOnClickListener(this@MainActivity)
        }

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_start -> {
                if(mainBinding.etNumberInput.text.toString().isEmpty() || mainBinding.etNumberInput.text.length < 4) {
                    Toast.makeText(this, "Time Invalid", Toast.LENGTH_SHORT).show()
                } else {
                    (viewModel as MainActivityViewModel).timerInputValue.value = mainBinding.etNumberInput.text.toString().toLong()
                    (viewModel as MainActivityViewModel).startTimer()
                }

            }
            R.id.btn_stop -> {
                (viewModel as MainActivityViewModel).stopStimer()
                mainBinding.tvCountdown.text = "0"
            }
        }
    }

}