package com.cs407.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.math.BigDecimal
import java.math.MathContext

class MainActivity : AppCompatActivity() {

    fun performCalculation(operator: String) {
        val numberOne = findViewById<EditText>(R.id.ediFirstNumber).text.toString().toBigDecimal()
        val secondNumber = findViewById<EditText>(R.id.editSecondNumber).text.toString().toBigDecimal()

        var result: BigDecimal?;

        when (operator) {
            "+" -> result = numberOne.add(secondNumber)
            "-" -> result = numberOne.minus(secondNumber)
            "*" -> result = numberOne.multiply(secondNumber)
            "/" -> {
                if (secondNumber.compareTo(BigDecimal.ZERO) != 0) {
                    result = numberOne.divide(secondNumber, MathContext.DECIMAL128)
                } else {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                    return
                }
            }
            else -> {
                Toast.makeText(this, "Unknown operator", Toast.LENGTH_SHORT).show()
                return
            }
        }

        //Create an intent to navigate to CalculatorActivity
        val intent = Intent(this, ResultActivity::class.java)

        // Pass the user input to the new activity
        intent.putExtra("EXTRA_MESSAGE", result.toString())

        // Start the new activity
        startActivity(intent)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var plusButton = findViewById<Button>(R.id.plusButton)
        var minusButton = findViewById<Button>(R.id.minusButton)
        var multiplicationButton = findViewById<Button>(R.id.multiplicationButton)
        var divisionButton = findViewById<Button>(R.id.divisionButton)

        plusButton.setOnClickListener {
            performCalculation("+")
        }

        minusButton.setOnClickListener {
            performCalculation("-")
        }

        multiplicationButton.setOnClickListener {
            performCalculation("*")
        }

        divisionButton.setOnClickListener {
            performCalculation("/")
        }


    }
}