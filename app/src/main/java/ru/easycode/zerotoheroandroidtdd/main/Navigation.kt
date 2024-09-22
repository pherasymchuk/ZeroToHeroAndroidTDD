package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.core.ProvideLiveData

interface Navigation {

    interface UpdateScreen : Navigation {
        fun update(newScreen: Screen)
    }

    interface Mutable : UpdateScreen, ProvideLiveData<Screen>

    class Default(
        private val liveDataWrapper: LiveDataWrapper.Mutable<Screen> = LiveDataWrapper.Default(),
    ) : Mutable {
        override fun update(newScreen: Screen) {
            liveDataWrapper.update(newScreen)
        }

        override fun liveData(): LiveData<Screen> {
            return liveDataWrapper.liveData()
        }
    }
}
