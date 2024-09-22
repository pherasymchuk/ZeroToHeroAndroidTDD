package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.ListScreen
import ru.easycode.zerotoheroandroidtdd.core.ProvideLiveData

abstract class MainViewModel : ViewModel(), ProvideLiveData<Screen> {
    abstract fun init(firstRun: Boolean)

    class Default(private val navigation: Navigation.Mutable) : MainViewModel() {
        override fun init(firstRun: Boolean) {
            if (firstRun) {
                navigation.update(ListScreen)
            }
        }

        override fun liveData(): LiveData<Screen> = navigation.liveData()
    }
}
