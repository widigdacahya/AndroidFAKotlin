package com.cahyadesthian.theviewmodel

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var result = 0.0

    fun calculate(width: Double, height: Double, length:Double) {
        result = width*height*length
    }

}