package ru.easycode.zerotoheroandroidtdd.fakes

import ru.easycode.zerotoheroandroidtdd.data.UiState
import ru.easycode.zerotoheroandroidtdd.wrappers.BundleWrapper

interface FakeBundleWrapper : BundleWrapper.Mutable {

    class Base : FakeBundleWrapper {

        private var uiState: UiState? = null

        override fun saveState(uiState: UiState) {
            this.uiState = uiState
        }

        override fun restoreState(): UiState = uiState!!
    }
}
