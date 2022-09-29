package ua.university.ui.util

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

typealias ViewModelCreator<VM> = () -> VM

class ViewModelFactory<VM : ViewModel>(
    private val viewModelCreator: ViewModelCreator<VM>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelCreator() as T
    }

}

inline fun <reified VM : ViewModel> ComponentActivity.viewModelCreator(
    noinline creator: ViewModelCreator<VM>
): Lazy<VM> {
    return viewModels { ViewModelFactory(creator) }
}