package ua.university.ui.util

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

typealias ViewModelCreator<VM> = () -> VM

class ViewModelFactory<VM : ViewModel>(
    private val viewModelCreator: ViewModelCreator<VM>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelCreator() as T
    }

}

@Suppress("MissingJvmstatic")
@Composable
inline fun <reified VM : ViewModel> viewModel(noinline creator: ViewModelCreator<VM>): VM {
    return viewModel(factory = ViewModelFactory(creator))
}