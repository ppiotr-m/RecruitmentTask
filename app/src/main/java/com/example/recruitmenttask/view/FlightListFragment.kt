package com.example.recruitmenttask.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recruitmenttask.databinding.FragmentFlightListBinding
import com.example.recruitmenttask.databinding.FragmentFlightSearchBinding

class FlightListFragment : Fragment() {

    private lateinit var binding: FragmentFlightListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightListBinding.inflate(inflater)

        return binding.root
    }
}