package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.network.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.wrappers.LiveDataWrapper

interface LoadResult {
    fun show(mutableLiveData: LiveDataWrapper.Mutable)

    data class Success(private val data: SimpleResponse) : LoadResult {
        override fun show(mutableLiveData: LiveDataWrapper.Mutable) {
            mutableLiveData.update(UiState.ShowData(text = data.text))
        }
    }

    data class Error(private val noConnection: Boolean) : LoadResult {
        private val text = if (noConnection) {
            "No internet connection"
        } else {
            "Something went wrong"
        }

        override fun show(mutableLiveData: LiveDataWrapper.Mutable) {
            mutableLiveData.update(
                value = UiState.ShowData(text)
            )
        }
    }
}
