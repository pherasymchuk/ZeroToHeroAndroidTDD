package ru.easycode.zerotoheroandroidtdd

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.easycode.zerotoheroandroidtdd.databinding.ItemElementBinding

private const val TAG = "logs"

abstract class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    abstract fun updateList(list: List<String>)

    class Default(
    ) : MainAdapter() {
        private val list: ArrayList<CharSequence> = ArrayList()

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
            list.clear()
            list.addAll(newList)
            Log.d(TAG, "updateList: new list content -> ${list.joinToString()}")
            notifyDataSetChanged()
        }

    }

    abstract class ViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(text: CharSequence)

        class Default(private val binding: ItemElementBinding) : ViewHolder(binding) {
            override fun bind(text: CharSequence) {
                binding.elementTextView.text = text
            }
        }
    }
}
