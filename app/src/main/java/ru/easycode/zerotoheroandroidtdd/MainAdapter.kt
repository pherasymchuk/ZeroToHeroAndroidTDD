package ru.easycode.zerotoheroandroidtdd

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.easycode.zerotoheroandroidtdd.databinding.ItemElementBinding

const val TAG = "logs"

abstract class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    abstract fun updateList(list: List<String>)

    class Default : MainAdapter() {
        private var list: List<String> = listOf()

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int,
        ): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return ViewHolder.Default(ItemElementBinding.inflate(layoutInflater, parent, false))
        }

        override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int,
        ) {
            holder.bind(list[position])
        }

        override fun getItemCount(): Int = list.size

        override fun updateList(newList: List<String>) {
            val diffCallback = MainDiffUtil(oldList = list, newList = newList)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            list = newList
            Log.d(TAG, "updateList: new list content -> ${list.joinToString()}")
            diffResult.dispatchUpdatesTo(this)
        }
    }

    class MainDiffUtil(
        private val oldList: List<String>,
        private val newList: List<String>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] === newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

    }

    abstract class ViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(text: String)

        class Default(private val binding: ItemElementBinding) : ViewHolder(binding) {
            override fun bind(text: String) {
                binding.elementTextView.text = text
            }
        }
    }
}
