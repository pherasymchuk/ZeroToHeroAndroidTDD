package ru.easycode.zerotoheroandroidtdd.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.core.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.core.ProvideLiveData
import ru.easycode.zerotoheroandroidtdd.core.SaveState
import ru.easycode.zerotoheroandroidtdd.core.SingleLiveEvent

interface ListLiveDataWrapper : SaveState, ProvideLiveData<List<CharSequence>> {

    interface Add : ListLiveDataWrapper {
        fun add(source: CharSequence)
    }

    interface Mutable : ListLiveDataWrapper {
        fun update(value: List<CharSequence>)
    }

    interface All : Mutable, Add

    class Default(
        private val liveData: MutableLiveData<List<CharSequence>> = SingleLiveEvent(),
    ) : All {
        override fun update(value: List<CharSequence>) {
            liveData.value = value
        }

        override fun liveData(): LiveData<List<CharSequence>> {
            return liveData
        }

        override fun saveState(bundleWrapper: BundleWrapper.Save) {
            val currentList = liveData.value ?: return
            bundleWrapper.save(ArrayList(currentList))
        }

        override fun add(source: CharSequence) {
//            val currentList = liveData.value ?: ArrayList()
//            val newList = ArrayList<CharSequence>()
//            newList.addAll(currentList)
//            newList.add(source)
            val newList = liveData.value?.toMutableList() ?: mutableListOf()
            newList.add(source)
            update(newList)
        }
    }
}
