package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * MainViewModel class that handles the business logic for the Main Activity.
 *
 * @param liveDataWrapper The LiveDataWrapper instance for managing the UI state.
 * @param repository The repository instance for data access.
 */
class MainViewModel(
    private val liveDataWrapper: LiveDataWrapper<UiState>,
    private val repository: Repository,
) : ViewModel() {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    /**
     * Loads data from the repository.
     *
     * - Updates the UI state to [UiState.ShowProgress] while loading.
     * - Updates the UI state to [UiState.ShowData] after loading.
     *
     */
    fun load() {
        liveDataWrapper.updateState(UiState.ShowProgress)
        try {
            viewModelScope.launch {
                repository.load()
                liveDataWrapper.updateState(UiState.ShowData)
            }
        } catch (_: Exception) {
            liveDataWrapper.updateState(UiState.Error)
        }
    }

    /**
     * Returns the LiveData object from the liveDataWrapper.
     *
     * @return LiveData object of type UiState
     */
    fun liveData(): LiveData<UiState> {
        return liveDataWrapper.liveData()
    }

    fun save(bundleWrapper: BundleWrapper.Save<UiState>) {
        liveDataWrapper.saveState(bundleWrapper)
    }

    fun restore(bundleWrapper: BundleWrapper.Restore<UiState>) {
        val uiState = bundleWrapper.restore()
        liveDataWrapper.updateState(uiState)
    }
}
