package com.example.einloggohnegoogle.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.transform.RoundedCornersTransformation
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.data.Rezept
import com.example.einloggohnegoogle.databinding.FragmentDataBinding
import com.example.einloggohnegoogle.databinding.FragmentRezeptDetailBinding

class RezeptDetailFragment : Fragment() {

    private var recipeTitleView: View? = null

    val viewModel: FirebaseViewmodel by viewModels()
    private lateinit var binding: FragmentRezeptDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRezeptDetailBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val id = it.getString("id")
            Log.d("RezeptDetailFragment", "Received Rezept ID: $id")

            viewModel.insertRezeptData(Rezept())  // Je nachdem, ob Sie dies benötigen

            viewModel.rezeptdetail.observe(viewLifecycleOwner) {
                Log.e("detailfehler","beim übergeben in die Detail")
                binding.RezeptNameTV.text = it.name.toString()
                binding.RezeptZutatenTV.text = it.zutaten.toString()
                binding.RezeptZubereitungTV.text = it.zubereitung.toString()
                Log.e("detailfehler","beim übergeben in die Detail")
                Log.d("detailID", "$it")
            }
        }

        binding.RezeptBackBTN.setOnClickListener {
            val navController = findNavController()
            navController.navigate(RezeptDetailFragmentDirections.actionRezeptDetailFragmentToDataFragment())
        }
    }

}

