package com.example.cubit.navigation.flow

import androidx.lifecycle.ViewModel

interface NavigationFlow {

    fun <T : ViewModel> getViewModel(modelClass: Class<T>): T

}