package ru.easycode.zerotoheroandroidtdd.create

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import ru.easycode.zerotoheroandroidtdd.core.AbstractViewFragment
import ru.easycode.zerotoheroandroidtdd.core.ViewModelSource
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentCreateBinding

const val TAG = "Logs"

class ViewFragmentCreate : AbstractViewFragment<FragmentCreateBinding>() {
    private val viewModel: CreateViewModel by lazy {
        (activity as ViewModelSource).viewModel(CreateViewModel::class.java)
    }

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentCreateBinding = FragmentCreateBinding.inflate(layoutInflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ViewFragmentCreate was created")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Log.d(TAG, "handleOnBackPressed: ViewFragmentCreate was pressed")
            viewModel.goBack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ViewFragmentCreate was destroyed")
    }


}
