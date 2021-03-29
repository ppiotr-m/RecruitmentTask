package com.example.recruitmenttask.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recruitmenttask.databinding.FragmentFlightDetailBinding

class FlightDetailFragment : Fragment() {

    private lateinit var binding: FragmentFlightDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightDetailBinding.inflate(inflater)

        return binding.root
    }
}