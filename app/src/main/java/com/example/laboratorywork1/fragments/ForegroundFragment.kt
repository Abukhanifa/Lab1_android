package com.example.laboratorywork1.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.laboratorywork1.R
import com.example.laboratorywork1.services.MusicService

class ForegroundFragment : Fragment() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            startMusicService("START") // Start service after permission is granted
        } else {
            // Handle case where permission is denied (optional UI feedback)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_foreground, container, false)

        val btnStart = view.findViewById<Button>(R.id.btnStart)
        val btnPause = view.findViewById<Button>(R.id.btnPause)
        val btnStop = view.findViewById<Button>(R.id.btnStop)

        btnStart.setOnClickListener { checkAndStartService("START") }
        btnPause.setOnClickListener { checkAndStartService("PAUSE") }
        btnStop.setOnClickListener { checkAndStartService("STOP") }

        return view
    }

    private fun checkAndStartService(action: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                startMusicService(action)
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            startMusicService(action)
        }
    }

    private fun startMusicService(action: String) {
        val intent = Intent(requireContext(), MusicService::class.java).setAction(action)
        requireContext().startService(intent)
    }
}
