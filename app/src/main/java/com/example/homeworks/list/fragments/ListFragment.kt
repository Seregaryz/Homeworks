package com.example.homeworks.list.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homeworks.R
import com.example.homeworks.dao.AppDatabase
import com.example.homeworks.interfaces.OnFragmentInteractionListener
import com.example.homeworks.todo.ToDoAdapter
import com.example.homeworks.todo.ToDoItem
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.sql.Date

class ListFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var adapter: ToDoAdapter
    private lateinit var db: AppDatabase
    private lateinit var todoList: List<ToDoItem>
    private lateinit var mListener: OnFragmentInteractionListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase(view.context)
        todoList = ArrayList()
        launch {
            todoList = db.todoDao().getToDoList()
            adapter = ToDoAdapter(todoList, {
                mListener.onFragmentInteraction(CreateToDoFragment.newInstance(it))
            }) {
                deleteTodo(it)
            }
            rv_todos.apply {
                layoutManager = GridLayoutManager(view.context, 2)
                adapter = adapter
            }
        }
        mListener = activity as OnFragmentInteractionListener
        floating_action_button.setOnClickListener {
            mListener.onFragmentInteraction(CreateToDoFragment.newInstance(-1))
        }
    }

    private fun deleteTodo(id: Int) {
        launch {
            db.todoDao().deleteToDo(id)
            todoList = db.todoDao().getToDoList()
        }
        adapter.deleteItem(todoList)
    }

    fun getDataSource(): ArrayList<ToDoItem> = arrayListOf(
        ToDoItem(1, "Just do it", "Do it today", Date(System.currentTimeMillis()).toString())
    )

    companion object {
        fun newInstance(): ListFragment =
            ListFragment()
    }
}
