package com.example.einloggohnegoogle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.einloggohnegoogle.databinding.ItemRezeptBinding

class RezeptAdapter(


    private val viewModel: FirebaseViewmodel,
    private val dataset: List<Rezept>,
    private val navController: NavController

) : RecyclerView.Adapter<RezeptAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemRezeptBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRezeptBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }
    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rezept = dataset[position]
        holder.binding.rezeptNameTV.text = rezept.name
        holder.binding.zutatenTV.text = rezept.zutaten
        holder.binding.zubereitungTV.text = "Zubereitung: ${rezept.zubereitung}"

    }


}
