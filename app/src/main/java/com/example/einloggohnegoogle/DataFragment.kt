package com.example.einloggohnegoogle

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.example.einloggohnegoogle.databinding.FragmentDataBinding
import com.google.firebase.firestore.FirebaseFirestore

class DataFragment : Fragment() {

    private val firestore = FirebaseFirestore.getInstance()

    val viewModel: FirebaseViewmodel by viewModels()
    private lateinit var binding: FragmentDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.rezeptDataList.observe(viewLifecycleOwner) { postsDataList ->
            binding.rezepteRecyclerView.adapter =
                RezeptAdapter(viewModel, postsDataList, NavController(requireContext()))
            Log.d("datafragment", "$postsDataList")
        }

        // "Rezepte" ist der Name der Sammlung in Firestore
        val rezepteDb = firestore.collection("Rezepte")

        //  alle Rezepte aus der Datenbank abrufen
        rezepteDb.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    // Hier gehen wir durch jedes Dokument (Rezept) in der Sammlung
                    val rezept = document.toObject(Rezept::class.java)
                    // Zeige das Rezept in den TextViews an
                    //   binding.rezeptNameTV.text = "Rezeptname: ${rezept.name}"
                    //   binding.zutatenTV.text = "Zutaten: ${rezept.zutaten.joinToString(", ")}"
                    //   binding.zubereitungTV.text = "Zubereitung: ${rezept.zubereitung}"
                }
            }
            .addOnFailureListener { exception ->
                Log.e("FirebaseTest", "Fehler beim Abrufen der Rezepte: ", exception)
            }

    }


}




