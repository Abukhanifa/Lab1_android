package com.example.laboratorywork1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.laboratorywork1.R
import com.example.laboratorywork1.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnIntents.setOnClickListener {
            findNavController().navigate(R.id.intentsFragment)
        }

        binding.btnForegroundService.setOnClickListener {
            findNavController().navigate(R.id.foregroundFragment)
        }

        binding.btnBackgroundService.setOnClickListener {
            findNavController().navigate(R.id.backgroundFragment)
        }

        binding.btnContentProvider.setOnClickListener {
            findNavController().navigate(R.id.contentProviderFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
