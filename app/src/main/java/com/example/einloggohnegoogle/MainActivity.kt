package com.example.einloggohnegoogle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        ///////////////////REZEPTE///////////////////////////////
        // Erstelle ein Rezept
        val rezept = Rezept(
            name = "Spaghetti Bolognese",
            zubereitung = "1. Zwiebel und Knoblauch würfeln und in Olivenöl glasig dünsten. 2. Hackfleisch hinzufügen und krümelig braten. 3. Tomatenmark und passierte Tomaten hinzufügen und ca. 30 Minuten köcheln lassen. 4. Spaghetti nach Packungsanleitung kochen und mit der Sauce servieren.",
            zutaten = listOf("500g Hackfleisch", "1 Zwiebel", "2 Knoblauchzehen", "2 EL Olivenöl", "1 EL Tomatenmark", "1 Dose passierte Tomaten", "Salz", "Pfeffer")
        )

        // Rufe die Methode "rezeptHinzufügen()" auf
        rezeptHinzufügen(rezept)
    }

    private fun rezeptHinzufügen(rezept: Rezept) {
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
    }
}