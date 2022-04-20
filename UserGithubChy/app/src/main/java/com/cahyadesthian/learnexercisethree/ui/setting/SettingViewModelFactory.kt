package com.cahyadesthian.learnexercisethree.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cahyadesthian.learnexercisethree.viewmodel.SettingViewModel

class SettingViewModelFactory(private val pref: SettingPreferences):
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SettingViewModel::class.java)) return SettingViewModel(pref) as T
        throw IllegalArgumentException("Unknown Viewmodel class: " + modelClass.name)
    }


}