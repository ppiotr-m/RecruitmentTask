package com.example.recruitmenttask.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.recruitmenttask.R
import com.example.recruitmenttask.databinding.FragmentFlightDetailBinding
import com.example.recruitmenttask.model.local.FlightDetailModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        val data = retriveData()

        if(data == null) {
            Toast.makeText(requireContext(), R.string.toast_error_message, Toast.LENGTH_LONG).show()
        } else {
            initViewWithData(data)
        }
    }

    private fun retriveData(): FlightDetailModel? {
        val bundle = arguments ?: return null
        return FlightDetailFragmentArgs.fromBundle(bundle).flightDetailData

    }

    private fun initViewWithData(data: FlightDetailModel) {
        binding.detailOriginTV.text = data.origin
        binding.detailDestinationTV.text = data.destination
        binding.detailInfantsLeftTV.text = data.infantsLeft.toString()
        binding.detailFareClassTV.text = data.fareClass
        binding.detailDiscountTV.text = data.discountInPercent.toString()
    }
}