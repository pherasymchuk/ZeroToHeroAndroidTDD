package ru.easycode.zerotoheroandroidtdd.create

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ClearViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.main.Navigation
import ru.easycode.zerotoheroandroidtdd.main.Screen

abstract class CreateViewModel : ViewModel() {
    abstract fun addItem(text: String)
    abstract fun goBack()

    class Default(
        private val addLiveDataWrapper: ListLiveDataWrapper.Add,
        private val navigation: Navigation.UpdateScreen,
        private val clearViewModel: ClearViewModel,
    ) : CreateViewModel() {
        override fun addItem(text: String) {
            addLiveDataWrapper.add(text)
            navigation.update(Screen.Pop)
            clearViewModel.clear(CreateViewModel::class.java)
        }

        override fun goBack() {
            navigation.update(Screen.Pop)
            clearViewModel.clear(CreateViewModel::class.java)
        }
    }
}
