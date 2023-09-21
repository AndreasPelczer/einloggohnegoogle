package com.example.einloggohnegoogle

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
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

        viewModel.loadfromFireStore()
            //RV wird beobachtet
        viewModel.rezeptDataList.observe(viewLifecycleOwner) { rezeptDataList ->
            binding.rezepteRecyclerView.adapter =
                RezeptAdapter(viewModel, rezeptDataList, NavController(requireContext()))
            ///hier wird die RV übergeben ______WICHTIG_______
            binding.rezepteRecyclerView.layoutManager = LinearLayoutManager(requireContext())

            Log.d("datafragment", "$rezeptDataList")
        }

    }
}





