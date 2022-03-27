package com.cahyadesthian.ridviewmodelfact

import android.util.Log
import androidx.lifecycle.ViewModel

const val TAG = "ViewModel"

class MainViewModel(name: String) : ViewModel() {

    var myName = name
    
    init {
        Log.d(TAG, "I am $myName")
    }
    
}