package com.example.countryapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.countryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var provinces: Array<String>
    private val countries = arrayOf(
        "Indonesia", "Malaysia", "Singapore", "Italia", "Inggris",
        "Belanda", "Argentina", "Chile", "Mesir", "Uganda"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        provinces = resources.getStringArray(R.array.provinces)

        with(binding) {
            // Adapter Spinner Country
            val adapterCountry = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, countries)
            adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCountry.adapter = adapterCountry

            // Adapter Spinner Province
            val adapterProvince = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, provinces)
            adapterProvince.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerprovinces.adapter = adapterProvince

            // Listener Spinner Country
            spinnerCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    Toast.makeText(this@MainActivity, countries[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Do nothing
                }
            }

            // Init DatePicker
            datePicker.init(datePicker.year, datePicker.month, datePicker.dayOfMonth) { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
            }

            // Listener TimePicker
            timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
                val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                Toast.makeText(this@MainActivity, selectedTime, Toast.LENGTH_SHORT).show()
            }

            // Listener Button Calendar Dialog
            btnShowCalendar.setOnClickListener {
                val datePickerFragment = DatePicker()
                datePickerFragment.show(supportFragmentManager, "datePicker")
            }

            // Listener Button Time Picker Dialog
            btnShowTimePicker.setOnClickListener {
                val timePickerFragment = TimePicker()
                timePickerFragment.show(supportFragmentManager, "timePicker")
            }
            btnShowCustumAlertDialog.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Keluar")
                builder.setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")

                builder.setPositiveButton("Ya") { dialog, which ->
                    //lakukan sesuatu ketika tombol positif diklik
                    finish()
                }

                builder.setNegativeButton("Tidak") { dialog, _ ->
                    //lakukan sesuatu ketika tombol negatif diklik
                    dialog.dismiss()
                }

                // Membuat dan menampilkan dialog
                val dialog = builder.create()
                dialog.show()
            }
            // Listener Button Custom Alert Dialog
            btnShowCustumAlertDialog.setOnClickListener {
                val dialog = DialogExit()
                dialog.show(supportFragmentManager, "dialogExit")
            }
        }
    }

    // Callback dari DatePickerDialog.OnDateSetListener
    override fun onDateSet(view: android.widget.DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val selectedDate = "$dayOfMonth/${month + 1}/$year"
        Toast.makeText(this, selectedDate, Toast.LENGTH_SHORT).show()
    }

    // FIX: Callback TimePicker Dialog
    override fun onTimeSet(view: android.widget.TimePicker?, hourOfDay: Int, minute: Int) {
        val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
        Toast.makeText(this, selectedTime, Toast.LENGTH_SHORT).show()
    }
}