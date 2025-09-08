package com.example.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var userInput: String = ""
    private var lastResult: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Number Buttons
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

        // Operators Buttons
        setNumberButton(binding.plus, "+")
        setNumberButton(binding.minus, "-")
        setNumberButton(binding.multiply, "*")
        setNumberButton(binding.percent, "%")
        setNumberButton(binding.division,"÷")
        setNumberButton(binding.dot, ".")

        // Clear All Button
        binding.clean.setOnClickListener {
            clearAll()
        }

        // Backspace Button
        binding.backspace.setOnClickListener {
            if (userInput.isNotEmpty()) {
                userInput = userInput.dropLast(1)
                binding.firstText.text = userInput
            }
        }

        // Equal Button
        binding.equal.setOnClickListener {
            calculateResult()
        }
    }

    // This function handles both numbers and operators
    private fun setNumberButton(button: android.view.View, value: String) {
        button.setOnClickListener {

            if (lastResult){

                // shift result history
                binding.sixthText.text = binding.fifthText.text
                binding.fifthText.text = binding.fourthText.text
                binding.fourthText.text = binding.thirdText.text
                binding.thirdText.text = binding.secondText.text
                binding.secondText.text = binding.firstText.text

                userInput = ""
                lastResult = false
            }
            // Add new character to input
            userInput += value
            binding.firstText.text = userInput
        }
    }

    // Reset everything
    private fun clearAll() {
        userInput = ""
        binding.firstText.text = ""
        binding.secondText.text = ""
        binding.thirdText.text = ""
        binding.fourthText.text = ""
        binding.fifthText.text = ""
        binding.sixthText.text = ""
    }

    // Calculate and show result
    private fun calculateResult() {
        if (userInput.isBlank()) return

        try {

            val expression = normalizeExpression(userInput)
            val result = ExpressionBuilder(expression).build().evaluate()
            val formatted = formateResult(result)

            // Shift history
            binding.sixthText.text = binding.fifthText.text
            binding.fifthText.text = binding.fourthText.text
            binding.fourthText.text = binding.thirdText.text
            binding.thirdText.text = binding.secondText.text
            binding.secondText.text = userInput

            // Show result
            binding.firstText.text = "= $formatted"
            userInput = ""
            lastResult = true

        } catch (e: Exception) {
            binding.firstText.text = "Error"
        }
    }

    // Replace symbols with valid operators
    private fun normalizeExpression(expr: String): String {
        var s  = expr

        s = s.replace("x", "*")
        s = s.replace("÷", "/")
        s = s.replace("%","/100")
        return s


    }


    // Formate result (int if no decimal, else double)
    private fun formateResult(result: Double): String {
        return if  (result == result.toLong().toDouble()){
            result.toLong().toString()
        }else{
            result.toString()
        }
    }
}
