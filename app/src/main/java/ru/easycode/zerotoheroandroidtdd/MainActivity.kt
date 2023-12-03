package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

interface Count {
    fun increment(number: String): UiState

    class Base(private val step: Int, private val max: Int) : Count {
        override fun increment(number: String): UiState {
            TODO()
        }
    }
}

interface UiState {

    class Base(text: String) : UiState

    class Max(text: String) : UiState
}
