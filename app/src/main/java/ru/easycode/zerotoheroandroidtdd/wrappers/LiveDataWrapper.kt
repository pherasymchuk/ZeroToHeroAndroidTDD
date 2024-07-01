package ru.easycode.zerotoheroandroidtdd.wrappers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.data.UiState

interface LiveDataWrapper {
    fun liveData(): LiveData<UiState>
    fun saveState(bundleWrapper: BundleWrapper.SaveState)
    fun update(value: UiState)

    class Base() : LiveDataWrapper {
        private val liveData: MutableLiveData<UiState> = MutableLiveData()

        override fun liveData(): LiveData<UiState> {
            return liveData
        }

        override fun saveState(bundleWrapper: BundleWrapper.SaveState): Unit = synchronized(this) {
            liveData.value?.let { bundleWrapper.saveState(it) }
        }

        override fun update(value: UiState) = synchronized(this) {
            liveData.value = value
        }
    }
}

interface SaveState {
    fun saveState(bundleWrapper: BundleWrapper.SaveState)
}
