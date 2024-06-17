package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface LiveDataWrapper : ProvideLiveData {
    fun update(value: UiState)

    class Default : LiveDataWrapper {
        private val liveData: MutableLiveData<UiState> = MutableLiveData()
        override fun update(value: UiState) {
            liveData.value = value
        }

        override fun liveData(): LiveData<UiState> {
            return liveData
        }
    }
}

interface ProvideLiveData {
    fun liveData(): LiveData<UiState>
}
