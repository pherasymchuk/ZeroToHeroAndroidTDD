package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputEditText.addTextChangedListener {
            binding.actionButton.isEnabled = it.toString().length >= 3
        }

        binding.actionButton.setOnClickListener {
            binding.titleTextView.text = binding.inputEditText.text
            binding.inputEditText.text?.clear()
        }
    }
}
