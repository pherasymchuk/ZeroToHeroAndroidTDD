package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map

interface ListLiveDataWrapper {
    fun liveData(): LiveData<List<CharSequence>>
    fun add(new: CharSequence)
    fun save(bundle: BundleWrapper.Save)
    fun update(list: List<CharSequence>)

    class Default(
        private val initialValue: ArrayList<CharSequence> = ArrayList(),
        private val liveData: MutableLiveData<ArrayList<CharSequence>> = SingleLiveEvent(),
    ) : ListLiveDataWrapper {
        init {
            liveData.value = initialValue
        }

        override fun liveData() = liveData.map { it.toList() }

        override fun add(new: CharSequence) {
            val list = liveData.value!!
            list.add(new)
            update(list)
        }

        override fun save(bundle: BundleWrapper.Save) {
            bundle.save(ArrayList(liveData.value!!))
        }

        override fun update(list: List<CharSequence>) {
            liveData.value = ArrayList(list)
        }
    }
}
