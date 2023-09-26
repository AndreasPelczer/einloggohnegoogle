package com.example.einloggohnegoogle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.einloggohnegoogle.databinding.FragmentTipBinding
import android.annotation.SuppressLint
import com.example.catfactsfriday.data.datamodels.FactsItem


class TipAdapter(
    private val dataset: List<FactsItem>
) : RecyclerView.Adapter<TipAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: FragmentTipBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentTipBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }


    @SuppressLint(/* ...value = */ "ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.textViewCV3.text = item.fact

    }
}