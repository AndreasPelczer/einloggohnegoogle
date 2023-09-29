package com.example.einloggohnegoogle.ui

import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.einloggohnegoogle.adapter.RezeptAdapter
import com.example.einloggohnegoogle.R


import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.ViewModels.MenuViewModel.MenuViewModel
import com.example.einloggohnegoogle.data.datamodels.MenuState
import com.example.einloggohnegoogle.databinding.FragmentDataBinding
import com.google.firebase.firestore.FirebaseFirestore

class DataFragment : Fragment() {


    private val firestore = FirebaseFirestore.getInstance()
    private val firestoreDocument = FirebaseFirestore.getInstance().collection("Rezepte").document("Rezept")
    val viewModel: FirebaseViewmodel by viewModels()
    private lateinit var binding: FragmentDataBinding
    private lateinit var menuViewModel: MenuViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.loadfromFireStore()
        binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        binding.rezepteRecyclerView.setHasFixedSize(true)
        viewModel.rezeptDataList.observe(viewLifecycleOwner) {rezept->
            Log.d("Homefragment","$rezept")
            binding.rezepteRecyclerView.adapter = RezeptAdapter(viewModel,rezept, NavController(requireContext())
            )
        }

        menuViewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        // Beobachte die LiveData "menuState" im ViewModel
        menuViewModel.menuState.observe(viewLifecycleOwner) { menuState: MenuState ->
            // Hier wird  der Menüzustand in der UI aktualisiert
            if (menuState.isOpen) {
                // Das Menü ist geöffnet, führe die entsprechende UI-Aktion durch
               // openMenu()
            } else {
               // closeMenu()
                // Das Menü ist geschlossen, führe die entsprechende UI-Aktion durch
            }
        }
        /* Hier füge einen Klicklistener für einen Button hinzu
        binding.openHamburgermenuIV.setOnClickListener {
            // Hier kannst du die Aktion auslösen, um das Menü zu öffnen
            menuViewModel.openMenu()
        }*/


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


        val neuesRezeptButton = binding.neuesRezeptBTN


        binding.neuesRezeptBTN.setOnClickListener {
            Log.d("ButtonClicked", "Neues Rezept Button wurde geklickt")
            val notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val mediaPlayer = MediaPlayer.create(context, notificationSound)
            mediaPlayer.start()
            val toast = Toast.makeText(requireContext(), "Eigenes Rezept erstellen", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
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
            }
        }
        binding.videobuttonBTN.setOnClickListener {
            Log.d("videoweg", "weiterleitung")
            findNavController().navigate(R.id.action_dataFragment_to_VideoFragment)
        }

        binding.tipBTN.setOnClickListener{
            findNavController().navigate(R.id.action_dataFragment_to_tipFragment)
        }

        binding.rezepteBTN.setOnClickListener{
            findNavController().navigate(R.id.dataFragment)
        }


    }




}





