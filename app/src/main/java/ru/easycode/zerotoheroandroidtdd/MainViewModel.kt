package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.wrappers.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.wrappers.LiveDataWrapper

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper,
    private val repository: Repository,
) {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScope.launch {
            val response: SimpleResponse = repository.load()
            liveDataWrapper.update(UiState.ShowData(response.text))
        }
    }

    fun saveState(bundleWrapper: BundleWrapper.SaveState) {
        liveDataWrapper.saveState(bundleWrapper)
    }

    fun restoreState(bundleWrapper: BundleWrapper.RestoreState) {
        liveDataWrapper.update(bundleWrapper.restoreState())
    }

    fun liveData(): LiveData<UiState> {
        return liveDataWrapper.liveData()
    }
}
