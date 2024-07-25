package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class MainViewModel : ViewModel() {
    abstract fun add(text: String)

    abstract fun save(bundle: BundleWrapper.Save)
    abstract fun restore(bundle: BundleWrapper.Restore)
    abstract fun liveData(): LiveData<List<CharSequence>>

    class Default(private val listLiveDataWrapper: ListLiveDataWrapper) : MainViewModel() {
        override fun add(text: String) {
            listLiveDataWrapper.add(text)
        }

        override fun save(bundle: BundleWrapper.Save) {
            listLiveDataWrapper.save(bundle)
        }

        override fun restore(bundle: BundleWrapper.Restore) {
            val list = bundle.restore()
            listLiveDataWrapper.update(list)
        }

        override fun liveData(): LiveData<List<CharSequence>> = listLiveDataWrapper.liveData()
    }
}
