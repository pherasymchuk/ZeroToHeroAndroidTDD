package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.network.SimpleService
import ru.easycode.zerotoheroandroidtdd.utils.Log
import java.io.InterruptedIOException
import java.net.UnknownHostException

interface Repository {
    suspend fun load(): LoadResult

    class Base(
        private val service: SimpleService,
        private val url: String,
        private val log: Log,
    ) : Repository {
        override suspend fun load(): LoadResult {
            return try {
                LoadResult.Success(data = service.fetch(url))
            } catch (_: UnknownHostException) {
                LoadResult.Error(noConnection = true)
            } catch (_: InterruptedIOException) {
                LoadResult.Error(noConnection = true)
            } catch (e: Exception) {
                log.log("Repository load error: ${e.message}")
                LoadResult.Error(noConnection = false)
            }
        }
    }
}

