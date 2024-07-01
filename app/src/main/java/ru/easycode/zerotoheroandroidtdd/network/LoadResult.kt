package ru.easycode.zerotoheroandroidtdd.network

import ru.easycode.zerotoheroandroidtdd.wrappers.LiveDataWrapper

interface LoadResult {
    fun show(mutableLiveData: LiveDataWrapper.Mutable)
    class Success(val data: SimpleResponse) : LoadResult {
        override fun show(mutableLiveData: LiveDataWrapper.Mutable) {
            TODO("Not yet implemented")
        }
    }

    class Error(private val noConnection: Boolean) : LoadResult {
        override fun show(mutableLiveData: LiveDataWrapper.Mutable) {
            TODO("Not yet implemented")
        }
    }
}
