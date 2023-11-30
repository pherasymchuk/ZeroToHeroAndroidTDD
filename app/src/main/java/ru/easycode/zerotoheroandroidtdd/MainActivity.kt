package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.parcelize.Parcelize
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var state: State = State.Initialized

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.removeButton.setOnClickListener {
            state = State.Removed
            state.apply(binding.rootLayout, binding.titleTextView, binding.removeButton)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(STATE_KEY, state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            state = savedInstanceState.getParcelable(STATE_KEY, State::class.java) as State
        } else {
            @Suppress("DEPRECATION")
            state = savedInstanceState.getParcelable(STATE_KEY) ?: State.Initialized
        }
        state.apply(binding.rootLayout, binding.titleTextView, binding.removeButton)
    }

    companion object {
        private const val STATE_KEY = "state"
    }
}

interface State : Parcelable {
    fun apply(linearLayout: LinearLayout, textView: TextView, button: Button)

    @Parcelize
    object Initialized : State {
        override fun apply(linearLayout: LinearLayout, textView: TextView, button: Button) {}
    }

    @Parcelize
    object Removed : State {
        override fun apply(linearLayout: LinearLayout, textView: TextView, button: Button) {
            linearLayout.removeView(textView)
            button.isEnabled = false
        }
    }
}
