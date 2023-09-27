package com.example.einloggohnegoogle.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    val viewmodel: FirebaseViewmodel by activityViewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpBTN.setOnClickListener {
            val email = binding.emailET.text.toString()
            val password = binding.passwordET.text.toString()

            viewmodel.signUp(email, password)
        }

        binding.signInBTN.setOnClickListener {
            val email = binding.emailET.text.toString()
            val password = binding.passwordET.text.toString()

            viewmodel.signIn(email, password)
        }

        //Wenn User eingeloggt ist, navigiere weiter
        viewmodel.user.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.dataFragment)
            }
        }

    }
}

