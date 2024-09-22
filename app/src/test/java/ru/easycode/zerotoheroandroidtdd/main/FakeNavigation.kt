package ru.easycode.zerotoheroandroidtdd.main

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals

interface FakeNavigation : Navigation.Mutable {

    fun checkUpdateCalled(expected: List<Screen>)

    class Default : FakeNavigation {

        private val callsList = mutableListOf<Screen>()

        override fun checkUpdateCalled(expected: List<Screen>) {
            assertEquals(expected, callsList)
        }

        override fun update(newScreen: Screen) {
            callsList.add(newScreen)
        }

        override fun liveData(): LiveData<Screen> {
            throw IllegalStateException("not used")
        }
    }
}
