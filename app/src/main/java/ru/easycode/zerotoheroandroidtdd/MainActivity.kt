package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var liveData: LiveData<List<CharSequence>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = (application as App).viewModel
        liveData = viewModel.liveData()
        adapter = MainAdapter.Default()

        binding.recyclerView.adapter = adapter

        binding.actionButton.setOnClickListener {
            val inputText = binding.inputEditText.text.toString()
            if (inputText.isBlank()) return@setOnClickListener
            viewModel.add(text = inputText.trim())
            binding.inputEditText.setText("")
        }

        liveData.observe(this) {
            Log.d(TAG, "livedata.observe: new value $it")
            adapter.updateList(it.map { it.toString() })
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(bundle = BundleWrapper.Default(outState))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel.restore(bundle = BundleWrapper.Default(savedInstanceState))
    }
}
