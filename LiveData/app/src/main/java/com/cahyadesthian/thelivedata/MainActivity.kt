package com.cahyadesthian.thelivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cahyadesthian.thelivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding

    //for connecting MainActivity and MainViewModel
    private lateinit var mLiveDataTimerViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        //connect MainActivity and MainViewModel
        mLiveDataTimerViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        //subcribe for livedata update :)
        subscribe()

    }


    /*
    * setiap ada perubahan dari metode tersebut,
    * maka akan mengubah TextView secara otomatis.
    * */
    private fun subscribe() {
        val elapsedTimeObserver = Observer<Long?> { aLong ->
            val newText = this@MainActivity.resources.getString(R.string.seconds, aLong)
            mainBinding.tvTimerUI.text = newText
        }

        //Jadi cara mendapatkan value dari sebuah LiveData harus dilakukan dengan
        // cara meng-observe LiveData tersebut.
        //Dan proses ini dilakukan secara asynchronous.
        mLiveDataTimerViewModel.getElapsedTime().observe(this,elapsedTimeObserver)

    }

}