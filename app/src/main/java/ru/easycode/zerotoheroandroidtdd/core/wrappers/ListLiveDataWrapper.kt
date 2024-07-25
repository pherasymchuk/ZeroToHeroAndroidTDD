package ru.easycode.zerotoheroandroidtdd.wrappers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.core.SingleLiveEvent
import ru.easycode.zerotoheroandroidtdd.ui.state.SaveState
import ru.easycode.zerotoheroandroidtdd.ui.state.UpdateState
import java.util.ArrayList

interface ListLiveDataWrapper : SaveState, UpdateState {
    fun liveData(): LiveData<ArrayList<String>>

    interface Mutable : ListLiveDataWrapper {
        fun add(new: String)
    }

    class Default(
        private val liveData: MutableLiveData<ArrayList<String>> = SingleLiveEvent(),
    ) : Mutable {

        override fun liveData(): LiveData<ArrayList<String>> {
            return liveData
        }

        override fun add(new: String) {
            val currentList = liveData.value ?: ArrayList<String>()
            Log.d("taggy", "ListLiveDataWrapper: Current list before adding: $currentList")
            currentList.add(new)
            Log.d("taggy", "add: New list after adding: $currentList\"")
            update(currentList)
        }

        override fun saveState(bundle: BundleWrapper.Save) {
            val state: List<String> = liveData.value ?: return
            val arrayList = ArrayList(state)
            bundle.save(arrayList)
        }

        override fun update(list: ArrayList<String>) {
            Log.d("taggy", "ListLiveDataWrapper: Updating LiveData with list: $list")
            liveData.value = list
        }
    }
}
