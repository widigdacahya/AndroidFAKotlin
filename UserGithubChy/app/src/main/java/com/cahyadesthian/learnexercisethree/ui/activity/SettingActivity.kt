package com.cahyadesthian.learnexercisethree.ui.activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.cahyadesthian.learnexercisethree.databinding.ActivitySettingBinding
import com.cahyadesthian.learnexercisethree.ui.setting.SettingPreferences
import com.cahyadesthian.learnexercisethree.ui.setting.SettingViewModelFactory
import com.cahyadesthian.learnexercisethree.viewmodel.SettingViewModel


/**
 * [Setting Night Mode]
 * Setting theme with SharedPreference
 * when we open app first time, it light mode
 * but when we activate dark mode then close the app
 * it would still in the dark mode
 * */
public val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class SettingActivity : AppCompatActivity() {

    private lateinit var settingBinding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingBinding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(settingBinding.root)
        val switchThemeBtn = settingBinding.switchBtnChangeSettingUI

        val pref = SettingPreferences.getInstance(dataStore)
        val settingViewModel = ViewModelProvider(this, SettingViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )

        settingViewModel.getThemeSettings().observe(this, { isDarkModeActive: Boolean ->
            if(isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchThemeBtn?.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchThemeBtn?.isChecked= false
            }
        })



        switchThemeBtn?.setOnCheckedChangeListener() { _: CompoundButton?, isChecked: Boolean ->
            settingViewModel.saveThemeSetting(isChecked)
        }




    }
}