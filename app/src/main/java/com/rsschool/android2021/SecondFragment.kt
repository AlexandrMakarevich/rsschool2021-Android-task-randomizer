package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class SecondFragment : Fragment() {

    private var backButton: Button? = null
    private var result: TextView? = null
    private var listener: SecondFragmentInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as SecondFragmentInterface
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val randomNumber = arguments?.getInt(RANDOM_VALUE_KEY) ?: 0

        result?.text = randomNumber.toString()

        backButton?.setOnClickListener {
            listener?.openFirstFragment(randomNumber)
        }
    }

    interface SecondFragmentInterface {
        fun openFirstFragment(randomNumber: Int)
    }

    companion object {

        @JvmStatic
        fun newInstance(randomNumber: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putInt(RANDOM_VALUE_KEY, randomNumber)
            fragment.arguments = args
            return fragment
        }

        private const val RANDOM_VALUE_KEY = "RANDOM_VALUE_KEY"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        backButton = null
        result = null
    }

}