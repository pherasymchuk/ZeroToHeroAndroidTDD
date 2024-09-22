package ru.easycode.zerotoheroandroidtdd.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.core.ViewModelSource
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ViewModelSource {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        viewModel = viewModel(MainViewModel::class.java)

        viewModel.liveData().observe(this) { newScreen ->
            newScreen.show(supportFragmentManager, binding.fragmentContainer.id)
        }

        viewModel.init(firstRun = savedInstanceState == null)

    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return (application as ViewModelSource).viewModel(viewModelClass)
    }
}
