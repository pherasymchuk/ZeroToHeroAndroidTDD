package ru.easycode.zerotoheroandroidtdd

import android.app.Application

class MainApplication : Application() {
    val viewModel = MainViewModel(
        liveDataWrapper = LiveDataWrapper.Default(),
        repository = Repository.Default()
    )
}
