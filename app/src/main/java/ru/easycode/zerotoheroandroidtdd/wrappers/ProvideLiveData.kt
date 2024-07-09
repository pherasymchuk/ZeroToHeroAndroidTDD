package ru.easycode.zerotoheroandroidtdd.wrappers

import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.data.UiState

interface ProvideLiveData {
    fun liveData(): LiveData<UiState>
}
