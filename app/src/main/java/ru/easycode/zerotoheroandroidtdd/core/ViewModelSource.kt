package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.create.CreateViewModel
import ru.easycode.zerotoheroandroidtdd.list.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.list.ListViewModel
import ru.easycode.zerotoheroandroidtdd.main.MainViewModel
import ru.easycode.zerotoheroandroidtdd.main.Navigation

interface ViewModelSource {
    fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T

    class Default : ViewModelSource {
        private val navigation = Navigation.Default()

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            return when (viewModelClass) {
                MainViewModel::class.java -> MainViewModel.Default(navigation)

                ListViewModel::class.java -> ListViewModel.Default(
                    ListLiveDataWrapper.Default(), navigation
                )

                CreateViewModel::class.java -> CreateViewModel.Default(
                    ListLiveDataWrapper.Default(),
                    navigation,
                    object : ClearViewModel {
                        override fun clear(viewModelClass: Class<out ViewModel>) {
                            // Empty for now
                        }

                    }
                )

                else -> throw IllegalStateException("Unknown ViewModel class")
            } as T
        }
    }
}
