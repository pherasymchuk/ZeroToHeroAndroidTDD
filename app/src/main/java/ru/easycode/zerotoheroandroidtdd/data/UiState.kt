package ru.easycode.zerotoheroandroidtdd.data

import android.os.Parcelable
import android.view.View
import kotlinx.parcelize.Parcelize
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

interface UiState : Parcelable {
    fun apply(binding: ActivityMainBinding)

    @Parcelize
    object ShowProgress : UiState {
        override fun apply(binding: ActivityMainBinding) {
            with(binding) {
                titleTextView.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
                actionButton.isEnabled = false
            }
        }
    }

    @Parcelize
    data class ShowData(private val text: String) : UiState {
        override fun apply(binding: ActivityMainBinding) {
            with(binding) {
                titleTextView.visibility = View.VISIBLE
                titleTextView.text = text
                progressBar.visibility = View.GONE
                actionButton.isEnabled = true
            }
        }
    }
}
