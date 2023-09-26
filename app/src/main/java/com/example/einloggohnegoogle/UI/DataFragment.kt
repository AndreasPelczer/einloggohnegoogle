package com.example.einloggohnegoogle.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.einloggohnegoogle.Adapter.RezeptAdapter
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.databinding.FragmentDataBinding
import com.google.firebase.firestore.FirebaseFirestore

class DataFragment : Fragment() {


    private val firestore = FirebaseFirestore.getInstance()
    private val firestoreDocument = FirebaseFirestore.getInstance().collection("Rezepte").document("Rezept")

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

        viewModel.loadfromFireStore()
            //RV wird beobachtet
        viewModel.rezeptDataList.observe(viewLifecycleOwner) { rezeptDataList ->
            Log.d("FirebaseLoad", "Received data from Firebase: $rezeptDataList")

            // Initialisiere den Adapter
            val rezeptAdapter = RezeptAdapter(viewModel, rezeptDataList, findNavController())

            // Verbinde den Adapter mit dem RecyclerView
            binding.rezepteRecyclerView.adapter = rezeptAdapter

            // Setze das Layout-Manager für den RecyclerView
            binding.rezepteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }


        binding.neuesRezeptBTN.setOnClickListener {
            Log.d("ButtonClicked", "Neues Rezept Button wurde geklickt")

            // Navigationsaktion auslösen
            Log.d("neuesRezept", "weiterleitung")
            findNavController().navigate(R.id.action_dataFragment_to_NeuesRezeptFragment)
        }

        binding.auslogBTN.setOnClickListener {
            Log.d("signout", "button gedrückt")

            viewModel.signOut()

        }
        viewModel.user.observe(viewLifecycleOwner){
            if (it == null){
                findNavController().navigate(R.id.action_dataFragment_to_logoutFragment)
            }
        }
        binding.videobuttonBTN.setOnClickListener {
            Log.d("videoweg", "weiterleitung")
            findNavController().navigate(R.id.action_dataFragment_to_VideoFragment)
        }



    }
}





