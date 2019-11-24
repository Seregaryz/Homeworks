package com.example.homeworks.ui.sixth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_sixth.*

class FIFAFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(com.example.homeworks.R.layout.fragment_sixth, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_first_fr_sixth.text = arguments?.getString(ARG_FIRST).toString()
        tv_sec_fr_sixth.text = arguments?.getString(ARG_SECOND).toString()
        tv_third_fr_sixth.text = arguments?.getString(ARG_THIRD).toString()
    }

    companion object {
        private const val ARG_FIRST = "FIRST"
        private const val ARG_SECOND = "SECOND"
        private const val ARG_THIRD = "THIRD"
        fun newInstance(first: String,
                        sec: String,
                        third: String): FIFAFragment =
            FIFAFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_FIRST, first)
                    putString(ARG_SECOND, sec)
                    putString(ARG_THIRD, third)
                }
            }
    }

}
