package ru.easycode.zerotoheroandroidtdd.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.core.ProvideLiveData
import ru.easycode.zerotoheroandroidtdd.core.RestoreState
import ru.easycode.zerotoheroandroidtdd.core.SaveState
import ru.easycode.zerotoheroandroidtdd.create.CreateScreen
import ru.easycode.zerotoheroandroidtdd.main.Navigation

abstract class ListViewModel : ViewModel(), SaveState, RestoreState {
    abstract fun create()

    class Default(
        private val liveDataList: ListLiveDataWrapper.Mutable,
        private val screenNavigation: Navigation.UpdateScreen,
    ) : ListViewModel(), ProvideLiveData<List<CharSequence>> {
        override fun create() {
//            liveDataList.update(listOf())
            screenNavigation.update(CreateScreen)
        }

        override fun saveState(bundleWrapper: BundleWrapper.Save) {
            liveDataList.saveState(bundleWrapper)
        }

        override fun restoreState(bundleWrapper: BundleWrapper.Restore) {
            val savedList = bundleWrapper.restore()
            liveDataList.update(savedList)
        }

        override fun liveData(): LiveData<List<CharSequence>> = liveDataList.liveData()
    }

}
