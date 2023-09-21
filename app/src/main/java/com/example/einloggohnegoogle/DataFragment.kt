package com.example.einloggohnegoogle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.einloggohnegoogle.databinding.FragmentDataBinding
import com.google.firebase.firestore.FirebaseFirestore

class DataFragment : Fragment() {

    private val firestore = FirebaseFirestore.getInstance()
    val viewmodel: FirebaseViewmodel by activityViewModels()
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