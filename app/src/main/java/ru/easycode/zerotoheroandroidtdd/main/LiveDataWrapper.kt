package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.core.ProvideLiveData
import ru.easycode.zerotoheroandroidtdd.core.SingleLiveEvent

interface LiveDataWrapper<T : Any> : ProvideLiveData<T> {

    interface Mutable<T : Any> : LiveDataWrapper<T> {
        fun update(newValue: T)
    }

    class Default<T : Any>(private val liveData: MutableLiveData<T> = SingleLiveEvent<T>()) : Mutable<T> {
        override fun liveData(): LiveData<T> {
            return liveData
        }

        override fun update(newValue: T) {
            liveData.value = newValue
        }
    }
}
