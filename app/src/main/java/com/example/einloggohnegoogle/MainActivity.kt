package com.example.einloggohnegoogle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

  /*  private fun rezeptHinzufügen(rezept: Rezept) {
        // Erstelle eine neue Instanz von FirebaseFirestore
        val firestore = FirebaseFirestore.getInstance()

        // Erstelle eine neue Dokumentensammlung mit dem Namen "Rezepte"
        val rezepteDb = firestore.collection("Rezepte")

        // Erstelle ein neues Dokument mit den Daten des Rezepts
        val dokument = rezepteDb.document()

        // Setze die Daten des Rezepts in das Dokument
        dokument.set(rezept)
            .addOnSuccessListener {
                // Erfolgreich aktualisiert
            }
            .addOnFailureListener { e ->
                // Fehler bei der Aktualisierung
            }
    }*/
}