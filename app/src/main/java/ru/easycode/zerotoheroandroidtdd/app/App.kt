package ru.easycode.zerotoheroandroidtdd.app

import android.app.Application
import ru.easycode.zerotoheroandroidtdd.ui.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.wrappers.ListLiveDataWrapper

class App : Application() {
    val mainViewModel: MainViewModel by lazy {
        MainViewModel.Default(ListLiveDataWrapper.Default())
    }
}
