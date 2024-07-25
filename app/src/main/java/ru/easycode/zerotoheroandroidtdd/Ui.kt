package ru.easycode.zerotoheroandroidtdd

import ru.easycode.zerotoheroandroidtdd.wrappers.ListLiveDataWrapper

interface Ui {
    fun update()

    class Views(
        private val liveDataWrapper: ListLiveDataWrapper,
    ) : Ui {
        override fun update() {

        }

    }
}
