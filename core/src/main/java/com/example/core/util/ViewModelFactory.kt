package com.example.core.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

typealias ViewModelCreator<VM> = () -> VM

/*class ViewModelFactory<VM : ViewModel>(
    private val viewModelCreator: ViewModelCreator<VM>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelCreator() as T
    }

}*/

class ViewModelF : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BassVM() as T
    }

}

class BassVM : ViewModel() {

}

/*
@Composable
inline fun <reified VM : ViewModel> ComponentActivity.viewModelCreator(
    noinline creator: ViewModelCreator<VM>
): Lazy<VM> {
    //return viewModel()

    val t: VM by //viewModels { ViewModelFactory(creator) }
    //return viewModels { ViewModelFactory(creator) }
}*/
