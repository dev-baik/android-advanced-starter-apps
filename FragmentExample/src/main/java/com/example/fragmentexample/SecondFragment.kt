package com.example.fragmentexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fragmentexample.databinding.FragmentSecondBinding

private const val YES = 0
private const val NO = 1

class SecondFragment : Fragment() {
    private lateinit var binding : FragmentSecondBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = binding.radioGroup.findViewById<RadioButton>(checkedId)
            when(binding.radioGroup.indexOfChild(radioButton)) {
                YES -> binding.fragmentHeader.setText(R.string.yes_message)
                NO -> binding.fragmentHeader.setText(R.string.no_message)
                else -> ""
            }
        }

        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Toast.makeText(context, "My Rating: $rating", Toast.LENGTH_SHORT).show()
        }
    }
}