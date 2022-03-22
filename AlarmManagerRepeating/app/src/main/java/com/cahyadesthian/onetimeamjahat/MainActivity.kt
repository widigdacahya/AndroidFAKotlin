package com.cahyadesthian.onetimeamjahat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cahyadesthian.onetimeamjahat.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min

class MainActivity : AppCompatActivity(), View.OnClickListener, DatePickerFragment.DialogDateListener, TimePickerFrgament.DialogTimeListener {

    companion object {
        private const val DATE_PICKER_TAG = "DatePicker"
        private const val TIME_PICKER_ONCE_TAG = "TimePickerOnce"
        private const val TIME_PICKER_REPEAT_TAG = "TimePickerRepeat"
    }

    private var mainBinding : ActivityMainBinding? = null
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding?.root)

        //listene one time alarm
        mainBinding?.btnOnceDate?.setOnClickListener(this)
        mainBinding?.btnOnceTime?.setOnClickListener(this)
        mainBinding?.btnSetOnceAlarm?.setOnClickListener(this)

        alarmReceiver = AlarmReceiver()
    }

    override fun onClick(v: View) {
        when(v.id) {

            R.id.btn_once_date -> {
                val datePickerFragment = DatePickerFragment()
                datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG)

            }

            R.id.btn_once_time -> {
                val timePickerFragmentOne = TimePickerFrgament()
                timePickerFragmentOne.show(supportFragmentManager, TIME_PICKER_ONCE_TAG)
            }

            R.id.btn_set_once_alarm -> {
                val onceDate = mainBinding?.tvOnceDate?.text.toString()
                val onceTime = mainBinding?.tvOnceTime?.text.toString()
                val onceMessage = mainBinding?.edtOnceMessage?.text.toString()

                alarmReceiver.setOneTimeAlarm(this,AlarmReceiver.TYPE_ONE_TIME,onceDate,onceTime,onceMessage)
            }
        }
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        // Siapkan date formatter-nya terlebih dahulu
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Set text dari textview once
        mainBinding?.tvOnceDate?.text = dateFormat.format(calendar.time)

    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {

        // Siapkan time formatter-nya terlebih dahulu
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)

        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        // Set text dari textview berdasarkan tag
        when (tag) {
            TIME_PICKER_ONCE_TAG -> mainBinding?.tvOnceTime?.text = dateFormat.format(calendar.time)
            TIME_PICKER_REPEAT_TAG -> {}
            else -> {
            }
        }

    }




    override fun onDestroy() {
        super.onDestroy()
        mainBinding = null
    }



}