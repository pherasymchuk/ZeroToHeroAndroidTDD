package ru.easycode.zerotoheroandroidtdd

import android.os.Parcelable
import android.widget.Button
import android.widget.TextView
import kotlinx.parcelize.Parcelize

interface UiState : Parcelable {
    fun apply(textView: TextView, button: Button)

    @Parcelize
    data class Base(val text: String) : UiState {
        override fun apply(textView: TextView, button: Button) {
            textView.text = text
        }
    }

    @Parcelize
    data class Max(val text: String) : UiState {
        override fun apply(textView: TextView, button: Button) {
            textView.text = text
            button.isEnabled = false
        }
    }
}
