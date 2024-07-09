package ru.easycode.zerotoheroandroidtdd.fakes

import org.junit.Assert
import ru.easycode.zerotoheroandroidtdd.data.LoadResult
import ru.easycode.zerotoheroandroidtdd.data.Repository

interface FakeRepository : Repository {

    fun expectResult(result: LoadResult)

    fun checkLoadCalledTimes(times: Int)

    class Base : FakeRepository {

        private lateinit var result: LoadResult

        override fun expectResult(result: LoadResult) {
            this.result = result
        }

        private var actualCalledTimes: Int = 0

        override fun checkLoadCalledTimes(times: Int) {
            Assert.assertEquals(times, actualCalledTimes)
        }

        override suspend fun load(): LoadResult {
            actualCalledTimes++
            return result
        }
    }
}
