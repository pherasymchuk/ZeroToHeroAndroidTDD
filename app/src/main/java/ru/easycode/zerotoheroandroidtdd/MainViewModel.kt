package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository,
) : ViewModel(), ProvideLiveData {
    private val coroutineState = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)
        coroutineState.launch {
            repository.load()
            liveDataWrapper.update(UiState.ShowData)
        }
    }

    override fun liveData(): LiveData<UiState> = liveDataWrapper.liveData()
}
