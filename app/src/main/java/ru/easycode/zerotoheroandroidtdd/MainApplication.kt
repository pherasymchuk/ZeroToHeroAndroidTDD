package ru.easycode.zerotoheroandroidtdd

import android.app.Application

class MainApplication : Application(), ProvideHomeViewModel {
    private val viewModel = MainViewModel(
        liveDataWrapper = LiveDataWrapper.Default(),
        repository = Repository.Default()
    )

    override fun viewModel(): MainViewModel = viewModel
}

interface ProvideHomeViewModel {
    fun viewModel(): MainViewModel
}
