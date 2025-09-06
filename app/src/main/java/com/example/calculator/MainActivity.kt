package com.example.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var userInput: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setNumberButton(binding.zero, "0")
        setNumberButton(binding.one, "1")
        setNumberButton(binding.two, "2")
        setNumberButton(binding.three, "3")
        setNumberButton(binding.four, "4")
        setNumberButton(binding.five, "5")
        setNumberButton(binding.six, "6")
        setNumberButton(binding.seven, "7")
        setNumberButton(binding.eight, "8")
        setNumberButton(binding.nine, "9")


        setNumberButton(binding.plus, "+")
        setNumberButton(binding.minus, "-")
        setNumberButton(binding.multiply, "x")
        setNumberButton(binding.division, "/")
        setNumberButton(binding.percent, "%")
        setNumberButton(binding.dot, ".")
    }

    private fun setNumberButton(button: android.view.View, value: String) {
        button.setOnClickListener {
            userInput += value
            binding.firstText.text = userInput
        }
    }
}
