package ru.easycode.zerotoheroandroidtdd

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Interface that represents a LiveData wrapper with SaveState functionality.
 *
 * @param T The type of data held by the LiveData.
 */
interface LiveDataWrapper<T : Parcelable> : ProvideLiveData<T>, SaveState<T> {
    fun updateState(uiState: T)

    /**
     * Default implementation of [LiveDataWrapper] for a generic type [T] that implements [Parcelable].
     *
     * This class wraps a [MutableLiveData] and provides methods to update the state, retrieve the LiveData, and save the state.
     *
     * @param T The type of data held by the LiveData.
     */
    class Default<T : Parcelable> : LiveDataWrapper<T> {
        private val liveData: MutableLiveData<T> = SingleLiveEvent()

        /**
         * Updates the value of the LiveData to the provided [uiState].
         *
         * @param uiState The new value to be set.
         */
        override fun updateState(uiState: T) {
            liveData.value = uiState
        }

        override fun liveData(): LiveData<T> = liveData

        /**
         * Saves the current value of the LiveData to the given [BundleWrapper.Save] object.
         *
         * - If the LiveData has a non-null value, it will be saved using the provided [bundle].
         * - If the LiveData is null or does not have a value, no action will be taken.
         *
         * @param bundle The [BundleWrapper.Save] object to save the value to.
         */
        override fun saveState(bundle: BundleWrapper.Save<T>) {
            liveData.value?.let(bundle::save)
        }

    }
}

interface ProvideLiveData<T> {
    fun liveData(): LiveData<T>
}

interface SaveState<T : Parcelable> {
    fun saveState(bundle: BundleWrapper.Save<T>)
}
