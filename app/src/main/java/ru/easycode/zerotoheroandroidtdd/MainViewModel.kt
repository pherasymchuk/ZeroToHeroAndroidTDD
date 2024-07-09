package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.easycode.zerotoheroandroidtdd.data.LoadResult
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.data.UiState
import ru.easycode.zerotoheroandroidtdd.wrappers.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.wrappers.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.wrappers.ProvideLiveData

class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper.Mutable,
    private val repository: Repository,
) : ProvideLiveData {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScope.launch {
            val response: LoadResult = repository.load()
            response.show(liveDataWrapper)
        }
    }

    fun saveState(bundleWrapper: BundleWrapper.SaveState) {
        liveDataWrapper.saveState(bundleWrapper)
    }

    fun restoreState(bundleWrapper: BundleWrapper.RestoreState) {
        liveDataWrapper.update(bundleWrapper.restoreState())
    }

    override fun liveData(): LiveData<UiState> {
        return liveDataWrapper.liveData()
    }
}
