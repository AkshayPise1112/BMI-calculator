package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etWeight = findViewById<EditText>(R.id.etNumber)
        val etHeight = findViewById<EditText>(R.id.etNumberHeight)
        val calculateButton = findViewById<Button>(R.id.btnCalculate)

        calculateButton.setOnClickListener {
            val weight = etWeight.text.toString()
            val height = etHeight.text.toString()

            if(validateInput(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))

                //control bmi range util two decimal points
                val ogbmi = String.format("%.2f", bmi).toFloat()

                printResult(ogbmi)
            }
        }
    }

    private fun validateInput(weight: String, height: String): Boolean {
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight Is Empty", Toast.LENGTH_LONG).show()
                return false
            }

            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height Is Empty", Toast.LENGTH_LONG).show()
                return false
            }

            else -> {
                return true
            }
        }
    }

    private fun printResult(bmi: Float) {
        val bmiText = findViewById<TextView>(R.id.tvIndex)
        val info = findViewById<TextView>(R.id.tvInfo)
        val result = findViewById<TextView>(R.id.tvResult)

        bmiText.text = bmi.toString()
        info.text = "(Normal Range is 18.5 - 24.9)"

        var resultText = ""
        var color = 0

        when{
            bmi < 18.5 -> {
                resultText = "UnderWeight"
                color = R.color.underweight
            }
            bmi in 18.5 .. 24.99 -> {
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 24.99 .. 29.99 -> {
                resultText = "OverWeight"
                color = R.color.overweight
            }
            bmi > 29.99 -> {
                resultText = "Obese"
                color = R.color.obese
            }
        }

        result.setTextColor(ContextCompat.getColor(this, color))
        result.text = resultText.toString()
    }
}