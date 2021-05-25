package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {


    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var min: EditText? = null
    private var max: EditText? = null
    private var listener: FirstFragmentInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FirstFragmentInterface
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
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        min = view.findViewById(R.id.min_value)
        max = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {
            if (min?.text.toString() == "" || max?.text.toString() == "") {
                Toast.makeText(context, "Please, input data!!!", Toast.LENGTH_SHORT).show()
            } else
                listener?.openSecondFragment(
                    min?.text.toString().toInt(),
                    max?.text.toString().toInt()
                )
        }
    }

    interface FirstFragmentInterface {
        fun openSecondFragment(min: Int, max: Int)
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }

    override fun onDestroyView() {
        generateButton = null
        previousResult = null
        min = null
        max = null

        super.onDestroyView()
    }
}