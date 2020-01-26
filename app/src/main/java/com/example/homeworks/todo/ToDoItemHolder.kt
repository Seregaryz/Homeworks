package com.example.homeworks.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworks.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.todo_list_item.*

class ToDoItemHolder(
    override val containerView: View,
    private val clickLambda: (Int) -> Unit,
    private val deleteLambda: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(toDoItem: ToDoItem) {
        containerView.apply {
            tv_title.text = toDoItem.title
            if (toDoItem.description.length > 54) tv_description.text =
                toDoItem.description.substring(0, 50)
            else tv_description.text = toDoItem.description
            tv_date.text = toDoItem.date

            itemView.setOnClickListener {
                clickLambda(toDoItem.id)
            }

            im_delete_todo.setOnClickListener {
                deleteLambda(toDoItem.id)
            }
        }
    }

    fun updateFromBundle(bundle: Bundle?) {
        containerView.apply {
            bundle?.apply {
                tv_title.text = getString(KEY_TITLE)
                tv_description.text = getString(KEY_DESCRIPTION)
                tv_date.text = getString(KEY_DATE)
            }
        }
    }

    companion object {

        const val KEY_ID = "id"
        const val KEY_TITLE = "title"
        const val KEY_DESCRIPTION = "content"
        const val KEY_DATE = "date"

        fun create(parent: ViewGroup, clickLambda: (Int) -> Unit, deleteLambda: (Int) -> Unit) =
            ToDoItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.todo_list_item, parent, false),
                clickLambda, deleteLambda
            )

    }

}