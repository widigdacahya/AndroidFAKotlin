package com.cahyadesthian.thelivedata

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {

    private val mInitialTime = SystemClock.elapsedRealtime()

    //[live data untk di subscribe MainActivity]
    private val mElapsedTime = MutableLiveData<Long?>()

    init {

        val timer = Timer()
        timer.scheduleAtFixedRate(object: TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) /1000

                //[live data untk di subscribe MainActivity]
                mElapsedTime.postValue(newValue)
                //we suse mutableLiveData that can change value
                //on above part


            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())


    }

    //[live data untk di subscribe MainActivity]
    fun getElapsedTime(): LiveData<Long?> {
        return mElapsedTime
    }

    companion object {
        private const val ONE_SECOND = 1000
    }

}