package com.fasoh.rxjavadisposables.flows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fasoh.rxjavadisposables.R
import com.fasoh.rxjavadisposables.models.Rate

class RateAdapter(private val items: List<Rate>) :
        RecyclerView.Adapter<RateAdapter.RateViewHolder>() {

        inner class RateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val currency = itemView.findViewById<TextView>(R.id.currency)
            private val ask = itemView.findViewById<TextView>(R.id.ask)
            private val bid = itemView.findViewById<TextView>(R.id.bid)

            fun bind(rate: Rate) {
                currency.text = rate.currency
                ask.text = rate.ask
                bid.text = rate.bid
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.rate_item, parent, false)
            return RateViewHolder(view)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
            holder.bind(items[position])
        }

    }