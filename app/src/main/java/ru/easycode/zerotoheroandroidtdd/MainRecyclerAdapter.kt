package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.easycode.zerotoheroandroidtdd.MainRecyclerAdapter.MainViewHolder
import ru.easycode.zerotoheroandroidtdd.databinding.ItemRecyclerViewBinding

abstract class MainRecyclerAdapter : RecyclerView.Adapter<MainViewHolder>(), SaveState, RestoreState,
    AddItem<String> {

    class Default(
    ) : MainRecyclerAdapter() {

        private var dataset: ArrayList<String> = ArrayList()

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int,
        ): MainViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemRecyclerViewBinding.inflate(layoutInflater)
            return MainViewHolder.Default(itemBinding)
        }

        override fun onBindViewHolder(
            holder: MainViewHolder,
            position: Int,
        ) {
            val newItemData = dataset[position]
            holder.update(newItemData)
        }

        override fun getItemCount(): Int = dataset.size

        override fun addItem(item: String) {
            dataset.add(item)
            notifyItemInserted(dataset.size) // More efficient than notifyDataSetChanged
        }

        override fun saveState(bundleWrapper: BundleWrapper) {
            bundleWrapper.saveStringArrayList(STATE_KEY, dataset)
        }

        override fun restoreState(bundleWrapper: BundleWrapper) {
            val state: ArrayList<String> = bundleWrapper.restoreStringArrayList(STATE_KEY)
            dataset = state
        }

        companion object {
            private const val STATE_KEY = "MainRecyclerAdapterState"
        }

    }

    abstract class MainViewHolder(
        itemBinding: ViewBinding,
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        abstract fun update(string: String)

        class Default(private val itemBinding: ItemRecyclerViewBinding) : MainViewHolder(itemBinding) {
            override fun update(string: String) {
                itemBinding.elementTextView.text = string
            }
        }
    }
}

interface AddItem<T> {
    fun addItem(item: T)
}
