package ru.easycode.zerotoheroandroidtdd.wrappers

import android.os.Build
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.UiState

interface BundleWrapper {

    interface SaveState {
        fun saveState(uiState: UiState)
    }

    interface RestoreState {
        fun restoreState(): UiState
    }

    interface Mutable : BundleWrapper, SaveState, RestoreState

    class Base(private val bundle: Bundle) : Mutable {

        override fun saveState(uiState: UiState) {
            bundle.putParcelable(KEY, uiState)
        }

        override fun restoreState(): UiState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(KEY, UiState::class.java)
        } else {
            bundle.getParcelable(KEY)
        } ?: UiState.ShowData("Hello world!")

        companion object {
            const val KEY = "uiState"
        }
    }
}
