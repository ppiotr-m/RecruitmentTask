package com.example.recruitmenttask.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recruitmenttask.R
import com.example.recruitmenttask.model.Flight
import com.example.recruitmenttask.model.local.FlightListModel
import com.example.recruitmenttask.view.interfaces.FlightListElementOnClickListener
import java.time.LocalDateTime

class AlternateFlightListAdapter(
    private val data: FlightListModel,
    private val listener: FlightListElementOnClickListener
) :
    RecyclerView.Adapter<AlternateFlightListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_flights, parent, false)

        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dateTV.text =
            LocalDateTime.parse(data.date).toLocalDate().toString()
        holder.durationTV.text = data.flightsList[position].duration
        holder.flightNumberTV.text = data.flightsList[position].flightNumber
        holder.priceTV.text =
            (data.flightsList[position].regularFare.fares[0].amount.toString() + " " + data.currency)
        holder.setItemPosition(position)
    }

    override fun getItemCount(): Int {
        return data.flightsList.size
    }

    class ViewHolder(view: View, private val listener: FlightListElementOnClickListener) :
        RecyclerView.ViewHolder(view) {

        private var itemPosition = -1

        val dateTV: TextView = view.findViewById(R.id.flightDateTV)
        val durationTV: TextView = view.findViewById(R.id.flightDurationTV)
        val flightNumberTV: TextView = view.findViewById(R.id.flightNrTV)
        val priceTV: TextView = view.findViewById(R.id.flightPriceTV)

        init {
            view.setOnClickListener {
                listener.onItemClicked(itemPosition)
            }
        }

        fun setItemPosition(itemPosition: Int) {
            this.itemPosition = itemPosition
        }
    }
}
