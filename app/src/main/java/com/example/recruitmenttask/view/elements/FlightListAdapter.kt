package com.example.recruitmenttask.view.elements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recruitmenttask.R
import com.example.recruitmenttask.model.Flight
import com.example.recruitmenttask.model.Trip
import java.time.LocalDate

class FlightListAdapter(private val data: List<Flight>, private val flightDate: String) :
    RecyclerView.Adapter<FlightListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_flights, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dateTV.text = flightDate
        holder.durationTV.text = data[position].duration.toString()
        holder.flightNumberTV.text = data[position].flightNumber
        holder.priceTV.text = data[position].regularFare.fares[0].amount.toString() //  TODO Work it out
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val dateTV: TextView = view.findViewById(R.id.flightDateTV)
        val durationTV: TextView = view.findViewById<TextView>(R.id.flightDurationTV)
        val flightNumberTV: TextView = view.findViewById<TextView>(R.id.flightNrTV)
        val priceTV: TextView = view.findViewById<TextView>(R.id.flightPriceTV)

        init {
            // Define click listener for the ViewHolder's View.

        }
    }
}