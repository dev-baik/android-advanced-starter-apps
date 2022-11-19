package com.example.fragmentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.fragmentexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var isFragmentDisplayed = false

    companion object {
        const val STATE_FRAGMENT = "state_of_fragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT)
            if(isFragmentDisplayed) {
                binding.openButton.setText(R.string.close)
            }
        }

        binding.openButton.setOnClickListener {
            if (!isFragmentDisplayed) {
                displayFragment()
            } else {
                closeFragment()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed)
        super.onSaveInstanceState(outState)
    }

    private fun displayFragment() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, SimpleFragment())
            .addToBackStack(null)
            .commit()
        binding.openButton.setText(R.string.close)
        isFragmentDisplayed = true
    }

    private fun closeFragment() {
        val fragmentManager = supportFragmentManager
        val simpleFragment = fragmentManager.findFragmentById(R.id.fragment_container)
        if (simpleFragment != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(simpleFragment).commit()
        }
        binding.openButton.setText(R.string.open)
        isFragmentDisplayed = false
    }
}