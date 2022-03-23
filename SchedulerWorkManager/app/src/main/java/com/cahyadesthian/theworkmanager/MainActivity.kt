package com.cahyadesthian.theworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.cahyadesthian.theworkmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var workManager: WorkManager
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        workManager = WorkManager.getInstance(this)
        mainBinding.btnOnetimetaskMainUI.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_onetimetask_mainUI -> startOneTimeTask()
        }
    }


    private fun startOneTimeTask() {
        mainBinding.tvTextStatusMainUI.text = getString(R.string.status)
        val data = Data.Builder()
            .putString(MyWorker.EXTRA_CITY, mainBinding.edtCityMainUI.text.toString())
            .build()
        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setInputData(data)
            .build()
        workManager.enqueue(oneTimeWorkRequest)
        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this@MainActivity, { workInfo ->
                val status = workInfo.state.name
                mainBinding.tvTextStatusMainUI.append("\n" + status)
            })
    }

}