package ru.easycode.zerotoheroandroidtdd

import kotlinx.coroutines.delay

interface Repository {
    suspend fun load()

    class Default : Repository {
        override suspend fun load() {
            delay(3000)
        }
    }
}
