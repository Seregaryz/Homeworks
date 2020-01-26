package com.example.homeworks.todo

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.homeworks.todo.ToDoItemHolder.Companion.KEY_DATE
import com.example.homeworks.todo.ToDoItemHolder.Companion.KEY_DESCRIPTION
import com.example.homeworks.todo.ToDoItemHolder.Companion.KEY_TITLE

class ToDoAdapter(
    private var dataSource: List<ToDoItem>,
    private var clickLambda: (Int) -> Unit,
    private var deleteLambda: (Int) -> Unit
): ListAdapter<ToDoItem, ToDoItemHolder>(object : DiffUtil.ItemCallback<ToDoItem>(){

    override fun areItemsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ToDoItem, newItem: ToDoItem): Boolean = oldItem == newItem

    override fun getChangePayload(oldItem: ToDoItem, newItem: ToDoItem): Any? {
        val diffBundle = Bundle()
        if (oldItem.title !== newItem.title) {
            diffBundle.putString(KEY_TITLE, newItem.title)
        }
        if (oldItem.description !== newItem.description) {
            diffBundle.putString(KEY_DESCRIPTION, newItem.description)
        }
        if (oldItem.date !== newItem.date) {
            diffBundle.putString(KEY_DATE, newItem.date)
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }

}) {

    override fun onBindViewHolder(holder: ToDoItemHolder, position: Int) =
        holder.bind(dataSource[position])

    override fun getItemCount(): Int = dataSource.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemHolder =
        ToDoItemHolder.create(
            parent,
            clickLambda,
            deleteLambda
        )

    override fun onBindViewHolder(
        holder: ToDoItemHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            val bundle = payloads[0] as? Bundle
            holder.updateFromBundle(bundle)
        }
    }

    private fun updateList(newList: List<ToDoItem>) {
        val result = DiffUtil.calculateDiff(
            ToDoDiffUtil(
                dataSource,
                newList
            ), true)
        result.dispatchUpdatesTo(this)
        val temp = dataSource.toMutableList()
        temp.clear()
        temp.addAll(newList)
        dataSource = temp.toList()
    }

    fun deleteItem(currList: List<ToDoItem>){
        updateList(currList)
    }
}