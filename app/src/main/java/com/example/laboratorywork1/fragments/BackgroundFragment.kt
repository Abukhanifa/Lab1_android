package com.example.laboratorywork1.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.laboratorywork1.R

class BackgroundFragment : Fragment() {

    private lateinit var statusTextView: TextView
    private lateinit var airplaneModeReceiver: AirplaneModeReceiver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_background, container, false)
        statusTextView = view.findViewById(R.id.statusTextView)
        return view
    }

    override fun onResume() {
        super.onResume()
        // Register the BroadcastReceiver dynamically
        airplaneModeReceiver = AirplaneModeReceiver()
        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        requireContext().registerReceiver(airplaneModeReceiver, filter)
    }

    override fun onPause() {
        super.onPause()
        // Unregister the receiver to prevent memory leaks
        requireContext().unregisterReceiver(airplaneModeReceiver)
    }


    // Inner class for the BroadcastReceiver
    inner class AirplaneModeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (Intent.ACTION_AIRPLANE_MODE_CHANGED == intent.action) {
                val isAirplaneModeOn = intent.getBooleanExtra("state", false)
                val message = if (isAirplaneModeOn) "Airplane Mode is ON" else "Airplane Mode is OFF"

                // Update the UI
                statusTextView.text = message

                // Show a Toast message
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
