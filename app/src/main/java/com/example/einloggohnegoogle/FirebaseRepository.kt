package com.example.einloggohnegoogle

import com.google.firebase.firestore.FirebaseFirestore

object FirebaseRepository {

    fun getAllRezepte(
        onSuccess: (List<Rezept>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val firestore = FirebaseFirestore.getInstance()
        val rezepteCollection = firestore.collection("Rezepte")

        rezepteCollection.get()
            .addOnSuccessListener { documents ->
                val rezepteList = mutableListOf<Rezept>()
                for (document in documents) {
                    val rezept = document.toObject(Rezept::class.java)
                    rezepteList.add(rezept)
                }
                onSuccess(rezepteList)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
}
