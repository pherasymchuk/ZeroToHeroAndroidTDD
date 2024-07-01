package ru.easycode.zerotoheroandroidtdd.wrappers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.SingleLiveEvent
import ru.easycode.zerotoheroandroidtdd.data.UiState

interface LiveDataWrapper {
    fun liveData(): LiveData<UiState>

    interface Update {
        fun update(value: UiState)
    }

    interface Mutable : LiveDataWrapper, SaveState {
        fun update(value: UiState)
    }

    class Default(
        private val liveData: MutableLiveData<UiState> = SingleLiveEvent(),
    ) : Mutable {
        override fun liveData(): LiveData<UiState> = liveData

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
