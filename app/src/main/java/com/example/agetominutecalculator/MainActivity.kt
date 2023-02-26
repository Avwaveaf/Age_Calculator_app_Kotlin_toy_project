package com.example.agetominutecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvSelectedDate: TextView
    private lateinit var tvResultInMin: TextView
    private  lateinit var tvResultAge: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btn_select_date)
        tvSelectedDate = findViewById(R.id.tv_selected_date)
        tvResultInMin = findViewById(R.id.tv_result_in_min)
        tvResultAge = findViewById(R.id.tv_result_age)
        btnDatePicker.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        triggerDatePicker()
    }

    fun triggerDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val date = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { view, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            tvSelectedDate.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate?.time ?: 0L / 60000

            val currentDate = Date()
            val currentDateInMin = currentDate.time / 60000

            val cal = Calendar.getInstance()
            val currYear = cal.get(Calendar.YEAR)

            val isFutureDate = theDate.after(currentDate)?: false

            val timeDifference = if(isFutureDate){
                selectedDateInMinutes - currentDateInMin
            }else{

                currentDateInMin - selectedDateInMinutes
            }
            val resultAge = currYear - year

            tvResultInMin.text = "$timeDifference"
            tvResultAge.text = "$resultAge"

        }, year, month, date).show()
    }

}