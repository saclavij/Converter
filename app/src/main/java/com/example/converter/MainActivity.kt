package com.example.converter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.converter.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayUnits("F ", "C ")
        binding.calculateButton.setOnClickListener { convertAmount() }
        binding.milesKilometers.setOnClickListener { displayUnits("mi", "Km") }
        binding.kilometersMiles.setOnClickListener { displayUnits("Km", "mi") }
        binding.fahrenheitCelsius.setOnClickListener { displayUnits("F", "C ") }
        binding.celsiusFahrenheit.setOnClickListener { displayUnits("C", "F ") }
        binding.gallonsLiters.setOnClickListener { displayUnits("gl", "l ") }
        binding.litersGallons.setOnClickListener { displayUnits("l ", "gl") }
        binding.inchesCentimeters.setOnClickListener { displayUnits("in", "cm") }
        binding.centimetersInches.setOnClickListener { displayUnits("cm", "in") }
    }

    private fun convertAmount() {

        //get the amount from the edit text
        val stringTextField = binding.amount.text.toString()
        val source = stringTextField.toDoubleOrNull()

        if (source == null) {
            displayResult(0.0)
            displayUnits(" ", "  ")
            return
        }

        //TODO Calculations

        //       var result = when (binding.convertOptions.checkedRadioButtonId) {
        var result = when (binding.convertOptions.checkedRadioButtonId) {

            R.id.miles_kilometers -> 1.60934 * source

            R.id.kilometers_miles -> 0.61371 * source

            R.id.gallons_liters -> 3.78541 * source

            R.id.liters_gallons -> .264172 * source

            R.id.fahrenheit_celsius -> (source - 32) * 5 / 9

            R.id.celsius_fahrenheit -> (source * 9 / 5) + 32

            R.id.inches_centimeters -> 2.54 * source

            else -> .393701 * source

        }

        //rounding the result if Round Up is on
        if (binding.roundUpSwitch.isChecked) {
            result = kotlin.math.ceil(result)
        }
        displayResult(result)

    }

    private fun displayResult(result: Double) {
        val formattedResult = NumberFormat.getInstance().format(result)
        //       binding.result.text = getString(R.string.result.formattedResult)
        binding.result.text = formattedResult

    }

    private fun displayUnits(sourceMeasUnit: String, targetMeasUnit: String) {
        binding.sourceMeasureUnit.text = sourceMeasUnit
        binding.targetMeasureUnit.text = targetMeasUnit
    }
}