package com.example.einloggohnegoogle

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await


class FirebaseRepository(

    private val rezeptDataBase: RezeptDataBase,
    private val firestore: FirebaseFirestore


) {

    private var lastKnownDocumentCount: Int = 0

    private val _firestoreRezept = MutableLiveData<List<Rezept>>()
    val firestoreRezept: LiveData<List<Rezept>> = _firestoreRezept
    private suspend fun fetchFirestoreData(): List<Rezept> {
        val firestoreData = mutableListOf<Rezept>()
        try {
            val firestoreCollection = firestore.collection("Rezepte")
            val querySnapshot: QuerySnapshot = firestoreCollection.get().await()

            // Überprüfen, ob es neue Daten gibt.//
            val currentDocumentCount = querySnapshot.size()
            if (currentDocumentCount > lastKnownDocumentCount) {
                for (document in querySnapshot.documents) {
                    val rezeptId = document.id
                    val name = document.getString("name") ?: ""
                    val zutaten = document.getString("zutaten") ?: ""
                    val zubereitung = document.getString("zubereitung") ?: ""


                    val rezept = Rezept(
                        id = rezeptId,
                        name = name,
                        zutaten = zutaten,
                        zubereitung = zubereitung,

                        )
                    Log.e(TAG, "get data: $rezept")
                    firestoreData.add(rezept)
                }
                // aktualisiere die letzte bekannte Anzahl von Dokumenten.//
                lastKnownDocumentCount = currentDocumentCount
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching Firestore data: $e")
        }
        return firestoreData
    }

    private fun saveRezeptToDatabase(apiData: List<Rezept>) {
        try {
            for (itemData in apiData) {
                // ueberprüfen , ob die Firestore id bereits in der Room-Datenbank vorhanden ist
                val existingRezept = rezeptDataBase.dao.getItemById(itemData.id)

                if (existingRezept == null) {
                    // wenn nicht vorhanden, füge hinzu.//
                    rezeptDataBase.dao.insertAll(itemData)
                } else {
                    // Wenn vorhanden, aktualisieren die daten in der Room DB.//
                    val updatedRezept = Rezept(
                        id = existingRezept.id,
                        name = itemData.name,
                        zutaten = itemData.zutaten,
                        zubereitung = itemData.zubereitung,

                        )
                    rezeptDataBase.dao.updateRezept(updatedRezept)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error inserting or updating data from API into database: $e")
        }
    }

    suspend fun getRezeptAndSaveToDatabase() {
        try {
            val firestoreData = fetchFirestoreData()
            _firestoreRezept.postValue(firestoreData)
            saveRezeptToDatabase(firestoreData)
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data: $e")
        }
    }

    fun getAll(): LiveData<List<Rezept>> {
        return rezeptDataBase.dao.getAllItems()
    }

}
