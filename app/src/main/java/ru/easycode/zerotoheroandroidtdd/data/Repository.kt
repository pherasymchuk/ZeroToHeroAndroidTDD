package ru.easycode.zerotoheroandroidtdd.data

import android.util.Log
import ru.easycode.zerotoheroandroidtdd.network.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.network.SimpleService
import ru.easycode.zerotoheroandroidtdd.wrappers.LiveDataWrapper
import java.io.InterruptedIOException
import java.net.UnknownHostException

interface Repository {
    suspend fun load(): LoadResult

    class Base(private val service: SimpleService, private val url: String) : Repository {
        val TAG = "tag"
        override suspend fun load(): LoadResult {
            return try {
                LoadResult.Success(data = service.fetch(url))
            } catch (_: UnknownHostException) {
                LoadResult.Error(noConnection = true)
            } catch (_: InterruptedIOException) {
                LoadResult.Error(noConnection = true)
            } catch (e: Exception) {
                Log.e(TAG, "load: $e")
                LoadResult.Error(noConnection = false)
            }
        }
    }
}

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
