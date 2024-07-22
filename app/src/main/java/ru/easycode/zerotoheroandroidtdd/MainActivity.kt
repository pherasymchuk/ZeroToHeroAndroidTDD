package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainAdapter = MainRecyclerAdapter.Default()
        with(binding.recyclerView) {
            adapter = mainAdapter
        }
        binding.actionButton.setOnClickListener {
            val inputText = binding.inputEditText.text
            if (inputText == null) return@setOnClickListener

            mainAdapter.addItem(inputText.toString())
            binding.inputEditText.text?.clear()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundleWrapper = BundleWrapper.Default(outState)
        mainAdapter.saveState(bundleWrapper)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val bundleWrapper = BundleWrapper.Default(savedInstanceState)
        mainAdapter.restoreState(bundleWrapper)
    }
}
