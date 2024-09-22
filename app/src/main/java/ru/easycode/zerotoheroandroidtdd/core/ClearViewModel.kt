package ru.easycode.zerotoheroandroidtdd.core

import androidx.lifecycle.ViewModel

interface ClearViewModel {
    fun clear(viewModelClass: Class<out ViewModel>)
}
