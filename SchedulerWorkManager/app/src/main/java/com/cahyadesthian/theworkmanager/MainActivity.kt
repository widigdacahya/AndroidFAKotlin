package com.cahyadesthian.theworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.work.*
import com.cahyadesthian.theworkmanager.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var workManager: WorkManager
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var periodicWorkRequest: PeriodicWorkRequest


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        workManager = WorkManager.getInstance(this)
        mainBinding.btnOnetimetaskMainUI.setOnClickListener(this)
        mainBinding.btnRunPeriodicTaskMainUI.setOnClickListener(this)
        mainBinding.btnCancelPeriodicTaskMainUI.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_onetimetask_mainUI -> startOneTimeTask()
            R.id.btn_run_periodic_task_mainUI -> startPeriodicTask()
            R.id.btn_cancel_periodic_task_mainUI -> cancelPeriodicTask()
        }
    }


    private fun startOneTimeTask() {
        mainBinding.tvTextStatusMainUI.text = getString(R.string.status)
        val data = Data.Builder()
            .putString(MyWorker.EXTRA_CITY, mainBinding.edtCityMainUI.text.toString())
            .build()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setInputData(data)
            .setConstraints(constraints)
            .build()

        workManager.enqueue(oneTimeWorkRequest)
        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this@MainActivity, { workInfo ->
                val status = workInfo.state.name
                mainBinding.tvTextStatusMainUI.append("\n" + status)
            })
    }


    private fun startPeriodicTask() {
        mainBinding.tvTextStatusMainUI.text = getString(R.string.status)

        val data = Data.Builder()
            .putString(MyWorker.EXTRA_CITY, mainBinding.edtCityMainUI.text.toString())
            .build()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        //15 disitu adlh menit, batasan minimal dari WorkManager
        periodicWorkRequest = PeriodicWorkRequest.Builder(MyWorker::class.java, 15, TimeUnit.MINUTES)
            .setInputData(data)
            .setConstraints(constraints)
            .build()
        workManager.enqueue(periodicWorkRequest)
        workManager.getWorkInfoByIdLiveData(periodicWorkRequest.id)
            .observe(this@MainActivity, { workInfo ->
                val status = workInfo.state.name
                mainBinding.tvTextStatusMainUI.append("\n" + status)
                mainBinding.btnCancelPeriodicTaskMainUI.isEnabled = false

                if (workInfo.state == WorkInfo.State.ENQUEUED) mainBinding.btnCancelPeriodicTaskMainUI.isEnabled = true


            })
    }

    private fun cancelPeriodicTask() {

        workManager.cancelWorkById(periodicWorkRequest.id)

    }


}