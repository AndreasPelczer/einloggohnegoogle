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

class DataFragment : Fragment() {

    val viewmodel: FirebaseViewmodel by activityViewModels()
    private lateinit var binding: FragmentDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.user.observe(viewLifecycleOwner){
            if(it == null){
                findNavController().navigate(R.id.loginFragment)
            }
        }

        viewmodel.profileRef.addSnapshotListener { value, error ->
            Log.d("FirebaseTest", "$value , ${value?.data}")
            value?.let {
                binding.roleTV.text = value["role"].toString()
            }

        }

    }

}