package ru.easycode.zerotoheroandroidtdd

import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.parcelize.Parcelize

interface UiState : Parcelable {
    fun apply(textView: TextView, button: Button, progressBar: ProgressBar)

    @Parcelize
    object ShowData : UiState {
        override fun apply(textView: TextView, button: Button, progressBar: ProgressBar) {
            textView.visibility = View.VISIBLE
            button.isEnabled = true
            progressBar.visibility = View.INVISIBLE
        }
    }

    @Parcelize
    object ShowProgress : UiState {
        override fun apply(textView: TextView, button: Button, progressBar: ProgressBar) {
            textView.visibility = View.INVISIBLE
            button.isEnabled = false
            progressBar.visibility = View.VISIBLE
        }
    }

    @Parcelize
    object Empty : UiState {
        override fun apply(textView: TextView, button: Button, progressBar: ProgressBar) {

        }
    }

    @Parcelize
    object Error : UiState {
        override fun apply(textView: TextView, button: Button, progressBar: ProgressBar) {
            textView.visibility = View.VISIBLE
            button.isEnabled = true
            progressBar.visibility = View.INVISIBLE
            textView.text = textView.context.getString(R.string.error_occurred_while_loading_data)
        }
    }
}
