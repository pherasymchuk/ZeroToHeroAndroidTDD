package ru.easycode.zerotoheroandroidtdd

import android.app.Application

class App : Application() {
    val viewModel: MainViewModel by lazy {
        MainViewModel.Default(
            listLiveDataWrapper = ListLiveDataWrapper.Default()
        )
    }
}
