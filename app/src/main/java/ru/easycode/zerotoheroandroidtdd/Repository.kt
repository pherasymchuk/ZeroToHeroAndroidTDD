package ru.easycode.zerotoheroandroidtdd

import kotlinx.coroutines.delay

interface Repository {
    suspend fun load()

    class Default() : Repository {
        /**
         * This function simulates a long-running operation by introducing a delay of 3 seconds.
         */
        override suspend fun load() {
            delay(3000)
        }
    }
}
