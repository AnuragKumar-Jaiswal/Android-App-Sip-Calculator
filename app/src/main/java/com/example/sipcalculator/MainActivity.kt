package com.example.sipcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        var editMonthlyInvestment: EditText = findViewById(R.id.editMonthlyInv)
        var editRateOfInterest: EditText = findViewById(R.id.rateOfInterest)
        var editPeriodOfInvestment: EditText = findViewById(R.id.NoOfYear)

        var calculationButton: Button = findViewById(R.id.calcButton)
        var investedAmountText: TextView = findViewById(R.id.investmentAmount)
        var wealthAmountText: TextView = findViewById(R.id.wealthAmount)

        calculationButton.setOnClickListener {

            var monthlyInvestment: Double = editMonthlyInvestment.text.toString().toDouble()
            var rateOfInterest: Double = editRateOfInterest.text.toString().toDouble()
            var timePeriod: Double = editPeriodOfInvestment.text.toString().toDouble()

            var periodInMonth: Double = timePeriod * 12

            var rateOfInterestPerMonth: Double = rateOfInterest / 12

            var wealth = calculateWealth(monthlyInvestment, rateOfInterestPerMonth, periodInMonth)
            var formatWealth = currencyFormatter(wealth)

            var investedAmount: Double = 0.0
            for (i in 1..periodInMonth.toInt()) {
                investedAmount = investedAmount + monthlyInvestment
            }

            var formatInvestedAmount = currencyFormatter(investedAmount)

            investedAmountText.text = formatInvestedAmount
            wealthAmountText.text = formatWealth
        }


    }

    private fun calculateWealth(
        investment: Double,
        rateInt: Double,
        period: Double
    ): Double {
        var i = period.toInt()
        var amount = investment
        var amountOfNextMonth = investment

        for (count in 1..i) {
            var interest = amount * rateInt / 100

            //total amount
            amount = amount + interest + amountOfNextMonth
        }

        return amount
    }

    private fun currencyFormatter(number: Double): String {

        val numberFormat = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("en-IN"));
        numberFormat.maximumFractionDigits = 2
        val convert: String = numberFormat.format(number)
        return convert
    }
}