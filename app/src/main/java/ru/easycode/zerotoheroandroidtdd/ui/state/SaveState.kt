package ru.easycode.zerotoheroandroidtdd.ui.state

import ru.easycode.zerotoheroandroidtdd.wrappers.BundleWrapper

interface SaveState {
    fun saveState(bundle: BundleWrapper.Save)
}
