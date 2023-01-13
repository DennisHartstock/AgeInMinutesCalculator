package com.commcode.ageinminutescalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var buttonSelectDate: Button
    private lateinit var textViewSelectedDate: TextView
    private lateinit var textViewAgeInMinutes: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        buttonSelectDate.setOnClickListener {
            getSelectedDate()
        }
    }

    private fun getSelectedDate() {
        val calendar: Calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val convertedDayOfMonth: String = if (selectedDayOfMonth < 10) {
                    "0$selectedDayOfMonth"
                } else {
                    "$selectedDayOfMonth"
                }
                val convertedMonth: String = if (selectedMonth < 9) {
                    "0${selectedMonth + 1}"
                } else {
                    "${selectedMonth + 1}"
                }
                val selectedDate = "$convertedDayOfMonth.$convertedMonth.$selectedYear"
                textViewSelectedDate.text = selectedDate

                val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN)
                val date = simpleDateFormat.parse(selectedDate)
                val currentDate =
                    simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
                val ageInMinutes = currentDate!!.time / 60_000 - date!!.time / 60_000
                textViewAgeInMinutes.text = ageInMinutes.toString()
            },
            year,
            month,
            dayOfMonth
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 8_600_000
        datePickerDialog.show()
    }

    private fun initViews() {
        buttonSelectDate = findViewById(R.id.buttonSelectDate)
        textViewSelectedDate = findViewById(R.id.textViewSelectedDate)
        textViewAgeInMinutes = findViewById(R.id.textViewAgeInMinutes)
    }
}