package ru.easycode.zerotoheroandroidtdd.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.easycode.zerotoheroandroidtdd.core.AbstractViewFragment
import ru.easycode.zerotoheroandroidtdd.core.ViewModelSource
import ru.easycode.zerotoheroandroidtdd.create.TAG
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentListBinding

class ListFragment : AbstractViewFragment<FragmentListBinding>() {

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentListBinding = FragmentListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = (activity as ViewModelSource).viewModel(ListViewModel::class.java)

        binding.addButton.setOnClickListener {
            viewModel.create()
        }
        Log.d(TAG, "onViewCreated: ViewFragmentList was created")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ViewFragmentList was destroyed")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ViewFragmentList's view was destroyed")
    }

}
