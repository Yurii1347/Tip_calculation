package com.vytivskyi.calculatethetip

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vytivskyi.calculatethetip.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }

    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        if (stringInTextField.isEmpty()) {
            Toast.makeText(this@MainActivity, "Enter the cost of service", Toast.LENGTH_SHORT)
                .show()
        } else {
            val cost = stringInTextField.toDouble()

            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_fifteen_percent -> 0.15
                else -> 0.18
            }
            var tip = cost * tipPercentage
            val roundUp = binding.roundUpSwitch.isChecked

            if (roundUp) {
                tip = ceil(tip)
            }

            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

            binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        }

    }
}