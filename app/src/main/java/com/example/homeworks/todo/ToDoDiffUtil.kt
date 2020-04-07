package com.example.homeworks.todo

import androidx.recyclerview.widget.DiffUtil

class ToDoDiffUtil(
    private var oldItems: List<ToDoItem>,
    private var newItems: List<ToDoItem>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].title.equals(newItems[newItemPosition].title)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}