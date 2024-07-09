package ru.easycode.zerotoheroandroidtdd.fakes

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals
import ru.easycode.zerotoheroandroidtdd.data.UiState
import ru.easycode.zerotoheroandroidtdd.wrappers.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.wrappers.LiveDataWrapper
import java.lang.IllegalStateException
import kotlin.collections.last

interface FakeLiveDataWrapper : LiveDataWrapper.Mutable {

    fun checkUpdateCalls(expected: List<UiState>)

    class Base : FakeLiveDataWrapper {

        private val actualCallsList = mutableListOf<UiState>()

        override fun checkUpdateCalls(expected: List<UiState>) {
            assertEquals(expected, actualCallsList)
        }

        override fun saveState(bundleWrapper: BundleWrapper.SaveState) {
            bundleWrapper.saveState(actualCallsList.last())
        }

        override fun update(value: UiState) {
            actualCallsList.add(value)
        }

        override fun liveData(): LiveData<UiState> {
            throw IllegalStateException("Should not be called in FakeLiveDataWrapper")
        }
    }
}
