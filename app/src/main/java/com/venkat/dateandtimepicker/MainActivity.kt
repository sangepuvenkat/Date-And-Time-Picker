package com.venkat.dateandtimepicker


import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.venkat.dateandtimepicker.databinding.ActivityMainBinding
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    var simpleDateFormat = SimpleDateFormat("dd/MMM/YYYY", Locale.getDefault())
    var timeFormat = SimpleDateFormat("HH:mm:ss a", Locale.getDefault())
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //date picker
        binding?.datePicker?.setOnClickListener {
            var Dialog = DatePickerDialog(
                this, { _, year, month, dayOfMonth ->
                    val calendar = Calendar.getInstance()
                    calendar.set(year, month, dayOfMonth)
                    val dataFormat = simpleDateFormat.format(calendar.time)
                    binding?.datePicker?.setText(dataFormat)
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            ).apply {

                val Date1 = Calendar.getInstance()
                Date1.add(Calendar.DAY_OF_MONTH, -10)
                datePicker.minDate = Date1.timeInMillis

                val Date2 = Calendar.getInstance()
                Date2.add(Calendar.DAY_OF_MONTH, +10)
                datePicker.maxDate = Date2.timeInMillis

               setTheme(R.style.Base_Theme_DateAndTimePicker)
            }.show()
        }

        //Time Picker
        binding?.timePicker?.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                this,
                { _, hourOfDay, minute ->
                    val selectedTime = Calendar.getInstance()
                    selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    selectedTime.set(Calendar.MINUTE, minute)
                    if (hourOfDay >= 7 && hourOfDay < 19) {
                        val formattedTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(selectedTime.time)
                        Toast.makeText(this, "${formattedTime} Time Set", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this, "Please select a time between 7 AM and 7 PM", Toast.LENGTH_SHORT).show()
                    }
                },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY,),
                Calendar.getInstance().get(Calendar.MINUTE), false
            ).show()
        }
    }
}