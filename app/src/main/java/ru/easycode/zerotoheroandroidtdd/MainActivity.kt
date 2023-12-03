package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var state: UiState = UiState.Base("0")
    private val count: Count = Count.Base(2, 4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.incrementButton.setOnClickListener {
            state = count.increment(binding.countTextView.text.toString())
            state.apply(binding.countTextView, binding.incrementButton)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(UI_STATE_KEY, state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            state =
                savedInstanceState.getParcelable(UI_STATE_KEY, UiState::class.java) ?: UiState.Base(
                    "0"
                )
        } else {
            @Suppress("DEPRECATION")
            state = savedInstanceState.getParcelable(UI_STATE_KEY) ?: UiState.Base("0")
        }
        state.apply(binding.countTextView, binding.incrementButton)
    }

    companion object {
        private const val UI_STATE_KEY = "ui-state"
    }
}
