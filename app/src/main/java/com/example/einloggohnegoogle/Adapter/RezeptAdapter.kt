package com.example.einloggohnegoogle.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.einloggohnegoogle.UI.FirebaseViewmodel
import com.example.einloggohnegoogle.data.Rezept
import com.example.einloggohnegoogle.databinding.ItemRezeptBinding
import com.google.firebase.firestore.FirebaseFirestore

class RezeptAdapter(


    private val viewModel: FirebaseViewmodel,
    private val dataset: List<Rezept>,

    private val navController: NavController):

    RecyclerView.Adapter<RezeptAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemRezeptBinding) : RecyclerView.ViewHolder(binding.root)
    private val firestoreDocument = FirebaseFirestore.getInstance().collection("Rezepte").document("Rezept")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRezeptBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }
    override fun getItemCount(): Int {
        return dataset.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rezept = dataset[position]
        holder.binding.rezeptNameTV.text = "Name: ${rezept.name}"
        holder.binding.zutatenTV.text = "Zutaten: ${rezept.zutaten}"
        holder.binding.zubereitungTV.text = "Zubereitung: ${rezept.zubereitung}"

    }


}