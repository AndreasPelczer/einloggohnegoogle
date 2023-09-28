package com.example.einloggohnegoogle.ui

import com.example.einloggohnegoogle.adapter.TipAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.ViewModels.TipViewModel
import com.example.einloggohnegoogle.ViewModels.TipViewModelFactory
import com.example.einloggohnegoogle.databinding.FragmentTipBinding

class TipFragment : Fragment() {

    private val viewModel: TipViewModel by viewModels { TipViewModelFactory(requireActivity().application, 10) } // Hier ersetzt du 10 durch deine gewünschte Größe
    private lateinit var binding: FragmentTipBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.loadData()
        binding = FragmentTipBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find the ImageView in the fragment layout
        val imageView: ImageView = view.findViewById(R.id.tipBTN)

        // Set the image resource to the ImageView
        imageView.setImageResource(R.drawable.ede9aab6043917b249a4aec7abc0ea5e)


        binding.btnRefresh.setOnClickListener {
            viewModel.loadData()
        }
        binding.itemRv.setHasFixedSize(true)

        viewModel.items.observe(viewLifecycleOwner) { items ->
            binding.itemRv.adapter = TipAdapter(items)
            binding.itemRv.layoutManager = LinearLayoutManager(requireContext())
        }
        binding.button2.setOnClickListener {
            Log.d("Button Click", "Button clicked")

            // Navigiere zurück zur vorherigen Ansicht (equivalent zur Zurück-Taste)
            findNavController().popBackStack()
        }
    }


}
