package com.cahyadesthian.githubuserchy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {

    private val _isLoadingMain = MutableStateFlow(true)
    val isLoading = _isLoadingMain.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1500)
            _isLoadingMain.value = false
        }
    }
}