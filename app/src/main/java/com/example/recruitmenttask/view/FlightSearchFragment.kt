package com.example.recruitmenttask.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.recruitmenttask.R
import com.example.recruitmenttask.databinding.FragmentFlightSearchBinding

class FlightSearchFragment : Fragment() {

    private lateinit var binding: FragmentFlightSearchBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFlightSearchBinding.inflate(inflater)

        return binding.root
    }

    private fun init() {
        navController = findNavController()
    }

    fun navigateToListFragment() {
        navController.navigate(R.id.action_flightSearchFragment_to_flightListFragment)
    }

}