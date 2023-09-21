package com.example.einloggohnegoogle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RezepteAdapter(private val rezepteList: List<Rezept>) : RecyclerView.Adapter<RezepteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rezept, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rezept = rezepteList[position]
        holder.rezeptNameTV.text = rezept.name
        holder.zutatenTV.text = "Zutaten: ${rezept.zutaten.joinToString(", ")}"
        holder.zubereitungTV.text = "Zubereitung: ${rezept.zubereitung}"
    }

    override fun getItemCount(): Int {
        return rezepteList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rezeptNameTV: TextView = itemView.findViewById(R.id.rezeptNameTV)
        val zutatenTV: TextView = itemView.findViewById(R.id.zutatenTV)
        val zubereitungTV: TextView = itemView.findViewById(R.id.zubereitungTV)
    }
}
