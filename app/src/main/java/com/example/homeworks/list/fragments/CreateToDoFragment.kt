package com.example.homeworks.list.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.homeworks.R
import com.example.homeworks.dao.AppDatabase
import kotlinx.android.synthetic.main.fragment_create_to_do.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.sql.Date

class CreateToDoFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase(view.context)
        val id = arguments?.getInt(ARG_ID) ?: -1
        if (id != -1) {
            launch {
                db.todoDao().getToDoById(id)?.apply {
                    et_title.setText(title, TextView.BufferType.EDITABLE)
                    et_description.setText(description, TextView.BufferType.EDITABLE)
                    et_date.setText(date)
                }
            }
            activity?.title = "Edit"
        } else {
            activity?.title = "Save"
        }
        btn_save.setOnClickListener{
            val title = et_title.text.toString()
            val description = et_description.text.toString()
            val date = Date(System.currentTimeMillis()).toString()
            launch {
                db.todoDao().save(title, description, date)
            }
            activity?.onBackPressed()
        }
    }

    companion object {

        private const val ARG_ID = "id"

        fun newInstance(id: Int): CreateToDoFragment =
            CreateToDoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, id)
                }
            }

    }
}
