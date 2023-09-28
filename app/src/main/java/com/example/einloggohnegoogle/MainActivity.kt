package com.example.einloggohnegoogle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Beispiel Rezepte für Vorspeisen
        /*     val vorspeisen = mutableListOf<Rezept>()
               vorspeisen.add(
                   Rezept(
                       name = "Caprese-Salat",
                       zutaten = "Tomaten, Mozzarella, Basilikum, Balsamico, Olivenöl, Salz, Pfeffer",
                       zubereitung = "Tomaten und Mozzarella in Scheiben schneiden, mit Basilikumblättern garnieren. Mit Balsamico und Olivenöl beträufeln, mit Salz und Pfeffer würzen."
                   )
               )
       // Weitere Vorspeisen hinzufügen...

       // Beispiel Rezepte für Hauptgänge
               val hauptgaenge = listOf<Rezept>(
                   Rezept(
                       name = "Spaghetti Carbonara",
                       zutaten = "Spaghetti, Speck, Eier, Parmesan, Knoblauch, Salz, Pfeffer",
                       zubereitung = "Speck knusprig braten, Spaghetti kochen. Eier, Parmesan, Knoblauch vermischen und über die Spaghetti geben. Mit Speck, Salz und Pfeffer garnieren."
                   ),
                   Rezept(
                       name = "Gegrilltes Hühnchen mit Gemüse",
                       zutaten = "Hühnchenbrust, Paprika, Zucchini, Olivenöl, Gewürze",
                       zubereitung = "Hühnchen und Gemüse marinieren, grillen und servieren."
                   ),
                   Rezept(
                       name = "Pasta Carbonara",
                       zutaten = "Spaghetti, Eier, Speck, Parmesan, Knoblauch, Sahne",
                       zubereitung = "Nudeln kochen, Speck anbraten, mit Eiern und Käse vermengen."
                   ),
                   Rezept(
                       name = "Linsensuppe",
                       zutaten = "Linsen, Gemüsebrühe, Karotten, Zwiebeln, Knoblauch",
                       zubereitung = "Linsen mit Gemüse kochen, mit Gewürzen abschmecken."
                   ),
                   Rezept(
                       name = "Gefüllte Paprika",
                       zutaten = "Paprika, Hackfleisch, Reis, Tomatensoße, Käse",
                       zubereitung = "Paprika aushöhlen, mit Hackfleisch und Reis füllen, backen und mit Käse überbacken."
                   ),
                   Rezept(
                       name = "Hähnchen-Curry",
                       zutaten = "Hähnchenbrust, Kokosmilch, Currypaste, Gemüse, Reis",
                       zubereitung = "Hähnchen anbraten, Gemüse hinzufügen, mit Kokosmilch und Currypaste kochen, servieren mit Reis."
                   ),
                   Rezept(
                       name = "Lasagne",
                       zutaten = "Lasagneblätter, Hackfleisch, Tomatensoße, Bechamelsauce, Käse",
                       zubereitung = "Lasagneblätter schichten mit Hackfleisch, Tomatensoße, Bechamelsauce und Käse, backen bis goldbraun."
                   ),
                   Rezept(
                       name = "Rindersteak mit Kartoffelstampf",
                       zutaten = "Rindersteak, Kartoffeln, Butter, Milch, Salz, Pfeffer",
                       zubereitung = "Rindersteak braten, Kartoffeln kochen, stampfen, mit Butter und Milch vermengen, servieren."
                   ),
                   Rezept(
                       name = "Gemüse-Couscous",
                       zutaten = "Couscous, Gemüsebrühe, Paprika, Zucchini, Kichererbsen",
                       zubereitung = "Couscous mit Gemüsebrühe übergießen, Gemüse anbraten, vermengen und servieren."
                   ),
                   Rezept(
                       name = "Vegetarische Pizza",
                       zutaten = "Pizza-Teig, Tomatensoße, Käse, Paprika, Zwiebeln, Oliven, Pilze",
                       zubereitung = "Teig ausrollen, mit Tomatensoße bestreichen, Gemüse und Käse darauf verteilen, backen bis knusprig."
                   )
               )

       // Weitere Hauptgänge hinzufügen...

       // Beispiel Rezepte für Desserts
               val desserts = mutableListOf<Rezept>()
               desserts.add(
                   Rezept(
                       name = "Schokoladenmousse",
                       zutaten = "Schokolade, Sahne, Eier, Zucker",
                       zubereitung = "Schokolade schmelzen, Sahne steif schlagen. Eigelb und Zucker schaumig schlagen. Geschmolzene Schokolade unterheben, Sahne unterheben. Kühl stellen."
                   )
               )
       // Weitere Desserts hinzufügen...

       // Speichern der Rezepte in die Firebase Firestore-Datenbank
               val rezepte = mutableListOf<Rezept>()
               rezepte.addAll(vorspeisen)
               rezepte.addAll(hauptgaenge)
               rezepte.addAll(desserts)

               saveRezeptToFirestore(rezepte)


           }

           private fun saveRezeptToFirestore(rezepte: List<Rezept>) {
               val db = Firebase.firestore
               val rezepteCollection = db.collection("Rezepte")

               for (rezept in rezepte) {
                   rezepteCollection.add(rezept)
                       .addOnSuccessListener { documentReference ->
                           Log.d(
                               TAG,
                               "Rezept erfolgreich in Firestore gespeichert mit ID: ${documentReference.id}"
                           )
                       }
                       .addOnFailureListener { e ->
                           Log.w(TAG, "Fehler beim Speichern des Rezepts in Firestore", e)
                       }
               }

         }*/
    }
}