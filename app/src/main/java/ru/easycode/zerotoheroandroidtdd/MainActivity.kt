package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.parcelize.Parcelize

class MainActivity : AppCompatActivity() {
    private var state: State = State.Initial
    private lateinit var rootLayout: LinearLayout
    private lateinit var titleTextView: TextView
    private lateinit var removeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rootLayout = findViewById(R.id.rootLayout)
        titleTextView = findViewById(R.id.titleTextView)
        removeButton = findViewById(R.id.removeButton)

        removeButton.setOnClickListener {
            state = State.Removed
            state.apply(rootLayout, titleTextView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("key", state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getParcelable("key", State::class.java) ?: State.Initial
        } else {
            @Suppress("DEPRECATION")
            savedInstanceState.getParcelable("key") ?: State.Initial
        }
        state.apply(rootLayout, titleTextView)
    }
}

interface State : Parcelable {

    fun apply(linearLayout: LinearLayout, textView: TextView) = Unit

    @Parcelize
    object Initial : State, Parcelable {
        override fun apply(linearLayout: LinearLayout, textView: TextView) = Unit
    }

    @Parcelize
    object Removed : State, Parcelable {
        override fun apply(linearLayout: LinearLayout, textView: TextView) {
            linearLayout.removeView(textView)
        }
    }
}
