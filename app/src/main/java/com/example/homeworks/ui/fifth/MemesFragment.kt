package com.example.homeworks.ui.fifth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homeworks.R
import kotlinx.android.synthetic.main.fragment_fifth.*

class MemesFragment : Fragment() {

    private var listener : OnFifthFragmentMoveListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFifthFragmentMoveListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_fifth, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val first = et_first_fr_fifth?.text.toString()
        val sec = et_sec_fr_fifth?.text.toString()
        val third = et_third_fr_fifth?.text.toString()
        btn_fr_fifth?.setOnClickListener {
            listener?.onMoveListenerFromFifth(first, sec, third)
        }
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFifthFragmentMoveListener{
        fun onMoveListenerFromFifth(first: String, sec: String, third: String)
    }


    companion object {

        fun newInstance() : MemesFragment = MemesFragment()
    }
}
