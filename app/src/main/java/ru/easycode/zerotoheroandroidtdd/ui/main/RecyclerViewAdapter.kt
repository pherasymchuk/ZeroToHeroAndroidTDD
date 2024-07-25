package ru.easycode.zerotoheroandroidtdd.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.databinding.ItemTextBinding
import kotlin.concurrent.thread

abstract class RecyclerViewAdapter :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    abstract fun updateItems(newItems: ArrayList<String>)

    class Default(
    ) : RecyclerViewAdapter() {

        private val items = mutableListOf<String>()

        init {
            thread(true) {
                for (i in 0..50) {
                    Log.d("taggy", "Adapter items: ${items.joinToString()}")
                    Thread.sleep(2000)
                }
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int,
        ): ViewHolder {
            return ViewHolder.Default(ItemTextBinding.inflate(LayoutInflater.from(parent.context)))
        }

        override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int,
        ) {
            val chars: CharSequence = items[position]
            Log.d("taggy", "onBindViewHolder: chars to string = ${chars.toString()}")
            holder.bind(chars.toString())
        }

        override fun getItemCount(): Int = items.size

        override fun updateItems(newItems: ArrayList<String>) {
            items.clear()
            items.addAll(newItems.map { it.toString() })
            Log.d("loggy", ": items updated with new value = $items")
            notifyItemRangeChanged(0, newItems.lastIndex)
        }

    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(string: String)

        class Default(private val itemViewBinding: ItemTextBinding) : ViewHolder(itemViewBinding.root) {
            override fun bind(string: String) {
                itemViewBinding.elementTextView.text = string
            }
        }
    }
}
