package com.example.homeworks.ui.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homeworks.R
import kotlinx.android.synthetic.main.fragment_first.*

class ReviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_first, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textFor = "Vabim"
        tv_fr_first.text = textFor

    }

    companion object {
        fun newInstance() : ReviewFragment = ReviewFragment()
    }
}
