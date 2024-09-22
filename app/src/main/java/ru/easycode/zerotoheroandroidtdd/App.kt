package ru.easycode.zerotoheroandroidtdd

import android.app.Application
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ViewModelFactory
import ru.easycode.zerotoheroandroidtdd.core.ViewModelSource

class App : Application(), ViewModelSource {
    private val viewModelFactory: ViewModelFactory = ViewModelFactory.Default(
        ViewModelSource.Default()
    )

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return viewModelFactory.viewModel(viewModelClass)
    }
}
