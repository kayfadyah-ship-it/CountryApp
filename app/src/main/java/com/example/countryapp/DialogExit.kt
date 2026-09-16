package com.example.countryapp

import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import androidx.wear.compose.materialcore.is24HourFormat
import com.example.countryapp.databinding.ActivityDialogExitBinding
import java.text.DateFormat

class DialogExit : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val binding = ActivityDialogExitBinding.inflate(inflater)

        with(binding) {
            btnYes.setOnClickListener {
                requireActivity().finish()
            }
            btnNo.setOnClickListener {
                dismiss()
            }
        }

        builder.setView(binding.root)
        return builder.create()
    }
}