package com.example.einloggohnegoogle.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.data.datamodels.Rezept
import com.example.einloggohnegoogle.databinding.FragmentRezeptDetailBinding

class RezeptDetailFragment : Fragment() {

    private val viewModel: FirebaseViewmodel by viewModels()
    private lateinit var binding: FragmentRezeptDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRezeptDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            val id = it.getString("id")
            viewModel.loadRezeptDetail(id)
            Log.d("loadDetail", " to RezeptDetailFragment with ID: ${id}")

            viewModel.rezeptdetail.observe(viewLifecycleOwner) {
                binding.RezeptNameTV.text = it.name
                binding.RezeptZutatenTV.text = it.zutaten
                binding.RezeptZubereitungTV.text = it.zubereitung
            }
        }



        binding.RezeptBackBTN.setOnClickListener {
            val navController = findNavController()
            navController.navigate(RezeptDetailFragmentDirections.actionRezeptDetailFragmentToDataFragment())
        }

        binding.rezeptBearbeitenBTN.setOnClickListener {  }
    }

    // Annahme: Funktion, um ein Rezept basierend auf der ID zu erhalten
    private fun getRezeptById(id: Long): Rezept? {
        // Implementieren Sie die Logik, um das Rezept basierend auf der ID zu erhalten
        // Rückgabe des Rezepts oder null, wenn nicht gefunden
        return null
    }
}
