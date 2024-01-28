package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var state: UiState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

interface UiState {

    fun apply(binding: ActivityMainBinding)

    data class Base(private val text: String) : UiState {
        override fun apply(binding: ActivityMainBinding) {
            binding.decrementButton.isEnabled = true
            binding.incrementButton.isEnabled = true
        }
    }

    data class Min(private val text: String) : UiState {
        override fun apply(binding: ActivityMainBinding) {
            binding.incrementButton.isEnabled = true
            binding.decrementButton.isEnabled = false
        }
    }

    data class Max(private val text: String) : UiState {
        override fun apply(binding: ActivityMainBinding) {
            binding.incrementButton.isEnabled = false
            binding.decrementButton.isEnabled = false
        }
    }
}
