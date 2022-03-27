package com.cahyadesthian.thecountdown

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private lateinit var timer: CountDownTimer

    private var _seconds = MutableLiveData<Int>()

    var seconds : LiveData<Int> = _seconds

    private var _finished = MutableLiveData<Boolean>()
    var finished :LiveData<Boolean> = _finished

    var timerInputValue = MutableLiveData<Long>()


    fun startTimer() {
        timer = object: CountDownTimer(timerInputValue.value?.toLong()!!,1000) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished/1000
                _seconds.value = timeLeft.toInt()
            }

            override fun onFinish() {
                _finished.value = true
            }

        }.start()

    }

    fun stopStimer() {
        timer.cancel()
    }


}