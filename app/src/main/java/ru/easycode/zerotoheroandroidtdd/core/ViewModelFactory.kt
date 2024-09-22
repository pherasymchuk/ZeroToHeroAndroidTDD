package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

interface ViewModelFactory : ViewModelSource, ClearViewModel {

    class Default(private val viewModelSource: ViewModelSource) : ViewModelFactory {
        private val cache = mutableMapOf<Class<out ViewModel>, ViewModel>()

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return cache.getOrPut(viewModelClass) {
                viewModelSource.viewModel(viewModelClass)
            } as T
        }

        override fun clear(viewModelClass: Class<out ViewModel>) {
            cache.remove(viewModelClass)
        }
    }
}
