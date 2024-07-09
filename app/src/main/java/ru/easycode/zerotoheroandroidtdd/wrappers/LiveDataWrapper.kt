package ru.easycode.zerotoheroandroidtdd.wrappers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.data.UiState
import ru.easycode.zerotoheroandroidtdd.utils.SingleLiveEvent

interface LiveDataWrapper : ProvideLiveData {

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

        override fun update(value: UiState) {
            liveData.value = value
        }
    }
}
