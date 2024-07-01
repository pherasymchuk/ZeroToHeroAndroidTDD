package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.network.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.network.SimpleService

interface Repository {
    suspend fun load(): SimpleResponse

    class Base(private val service: SimpleService, private val url: String) : Repository {
        override suspend fun load(): SimpleResponse = service.fetch(url)
    }
}
