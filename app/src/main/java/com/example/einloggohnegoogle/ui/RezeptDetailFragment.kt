package com.example.einloggohnegoogle.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.einloggohnegoogle.R
import com.example.einloggohnegoogle.ViewModels.FirebaseViewmodel
import com.example.einloggohnegoogle.databinding.FragmentDataBinding

class RezeptDetailFragment : Fragment() {

    private var recipeTitleView: View? = null

    val viewModel: FirebaseViewmodel by viewModels()
    private lateinit var binding: FragmentDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rezept_detail, container, false)

        val rezeptBackButton: ImageView = view.findViewById(R.id.RezeptBackBTN)

        // Füge dem ImageView eine Klickaktion hinzu
        rezeptBackButton.setOnClickListener {
            // Hier kannst du die Aktion ausführen, die passieren soll, wenn der Button geklickt wird
            // Zum Beispiel den Nutzer zurücknavigieren
            activity?.onBackPressed()
        }

        return view
    }
}
