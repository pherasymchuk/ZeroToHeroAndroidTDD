package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import android.os.Parcelable

interface BundleWrapper<T : Parcelable> {
    interface Save<T : Parcelable> {
        fun save(uiState: T)
    }

    interface Restore<T> {
        fun restore(): T
    }

    interface HandleBundleState<T : Parcelable> : BundleWrapper<T>, Save<T>, Restore<T>

    class Default(private val bundle: Bundle) : HandleBundleState<UiState> {
        override fun save(uiState: UiState) {
            bundle.putParcelable(KEY, uiState)
        }

        override fun restore(): UiState {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(KEY, UiState::class.java)
            } else {
                bundle.getParcelable(KEY)
            } ?: UiState.ShowData
        }

        companion object {
            private const val KEY = "uiStateKey"
        }
    }
}
