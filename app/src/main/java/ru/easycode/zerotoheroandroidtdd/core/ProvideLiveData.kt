package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.LiveData

interface ProvideLiveData<T> {
    fun liveData(): LiveData<T>
}
