package ru.easycode.zerotoheroandroidtdd.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.easycode.zerotoheroandroidtdd.app.App
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.wrappers.BundleWrapper
import kotlin.lazy

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by lazy { (application as App).mainViewModel }
    private lateinit var recyclerAdapter: RecyclerViewAdapter.Default

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerAdapter = RecyclerViewAdapter.Default()
        binding.recyclerView.adapter = recyclerAdapter


        binding.actionButton.setOnClickListener {
            val inputText = binding.inputEditText.text.toString()
            Log.d("taggy", "MainActivity: Button clicked: inputText = $inputText")
            mainViewModel.add(inputText)
            binding.inputEditText.text?.clear()
        }

        mainViewModel.liveData().observe(this) { newList: ArrayList<String> ->
            Log.d("taggy", "MainActivity: New list observed: $newList")
            recyclerAdapter.updateItems(newList)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mainViewModel.saveState(BundleWrapper.Default(outState))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mainViewModel.restoreState(BundleWrapper.Default(savedInstanceState))
    }
}
