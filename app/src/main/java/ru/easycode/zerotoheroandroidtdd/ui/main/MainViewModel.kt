package ru.easycode.zerotoheroandroidtdd.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.ui.state.RestoreState
import ru.easycode.zerotoheroandroidtdd.ui.state.SaveState
import ru.easycode.zerotoheroandroidtdd.wrappers.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.wrappers.ListLiveDataWrapper

abstract class MainViewModel : ViewModel(), SaveState, RestoreState {
    abstract fun add(text: String)
    abstract fun liveData(): LiveData<ArrayList<String>>

    class Default(private val listLiveDataWrapper: ListLiveDataWrapper.Mutable) : MainViewModel() {
        override fun add(text: String) {
            Log.d("taggy", "MainViewModel: Adding text: $text")
            listLiveDataWrapper.add(text)
        }

        override fun liveData(): LiveData<ArrayList<String>> {
            return listLiveDataWrapper.liveData()
        }

        override fun saveState(bundle: BundleWrapper.Save) {
            listLiveDataWrapper.saveState(bundle)
        }

        override fun restoreState(bundle: BundleWrapper.Restore) {
            val state = bundle.restore()
            listLiveDataWrapper.update(state)
        }

    }
}
