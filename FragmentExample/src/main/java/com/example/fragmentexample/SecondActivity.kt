package com.example.fragmentexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentexample.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }
    private var isFragmentDisplayed = false

    companion object {
        private const val STATE_FRAGMENT = "state_of_fragment"
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
            if(!isFragmentDisplayed) {
                displayFragment()
            } else {
                closeFragment()
            }
        }

        binding.previousButton.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed)
        super.onSaveInstanceState(outState)
    }

    private fun displayFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, SecondFragment())
            .addToBackStack(null)
            .commit()

        binding.openButton.setText(R.string.close)
        isFragmentDisplayed = true
    }

    private fun closeFragment() {
        val fragmentManager = supportFragmentManager
        val secondFragment = fragmentManager.findFragmentById(R.id.fragment_container)
        if(secondFragment != null) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(secondFragment).commit()
        }
        binding.openButton.setText(R.string.open)
        isFragmentDisplayed = false
    }
}