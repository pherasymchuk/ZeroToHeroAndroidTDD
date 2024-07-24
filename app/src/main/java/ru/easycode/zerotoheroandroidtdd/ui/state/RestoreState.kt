package ru.easycode.zerotoheroandroidtdd.ui.state

import ru.easycode.zerotoheroandroidtdd.wrappers.BundleWrapper

interface RestoreState {
    fun restoreState(bundle: BundleWrapper.Restore)
}
