package com.example.homeworks.ui.fourth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homeworks.R
import kotlinx.android.synthetic.main.fragment_fourth.*

class PhotoFragment : Fragment() {

    private var listener : OnFourthFragmentMoveListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFourthFragmentMoveListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_fourth, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_fr_fourth.setOnClickListener {
            listener?.onMoveListenerFromFourth()
        }

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFourthFragmentMoveListener{
        fun onMoveListenerFromFourth()
    }

    companion object {

        fun newInstance() : PhotoFragment = PhotoFragment()
    }

}
