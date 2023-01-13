package com.commcode.ageinminutescalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var buttonSelectDate: Button

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

        DatePickerDialog(
            this,
            { _, _, _, _ ->
                Toast.makeText(this, "getSelectedDate works", Toast.LENGTH_SHORT).show()
            },
            year,
            month,
            dayOfMonth
        ).show()
    }

    private fun initViews() {
        buttonSelectDate = findViewById(R.id.buttonSelectDate)
    }
}