package com.example.einloggohnegoogle.Repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.einloggohnegoogle.data.Rezept
import com.example.einloggohnegoogle.data.RezeptDataBase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.UUID


class FirebaseRepository(

    private val rezeptDataBase: RezeptDataBase,
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth

) {
    private var lastKnownDocumentCount: Int = 0

    private val _firestoreRezept = MutableLiveData<List<Rezept>>()

    fun getCurrentUserId(): String? = auth.currentUser?.uid

    val firestoreRezept: LiveData<List<Rezept>> = _firestoreRezept
    private suspend fun fetchFirestoreData(): List<Rezept> {

        val firestoreData = mutableListOf<Rezept>()
        try {
            val firestoreCollection = firestore.collection("Rezepte")
            val querySnapshot: QuerySnapshot = firestoreCollection.get().await()
            Log.e("firestore laden", "Error fetching Firestore data:")

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
                // Wenn vorhanden, aktualisieren die daten in der Room DB.//
                val updatedRezept = Rezept(
                    id = existingRezept.id,
                    name = itemData.name,
                    zutaten = itemData.zutaten,
                    zubereitung = itemData.zubereitung,

                    )
                rezeptDataBase.dao.updateRezept(updatedRezept)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error inserting or updating data from API into database: $e")
        }
    }

    suspend fun getRezeptAndSaveToDatabase() {
        try {
            val firestoreData = fetchFirestoreData()
            Log.e(TAG, "FirestoreDatato: $firestoreData")

            _firestoreRezept.postValue(firestoreData)
            saveRezeptToDatabase(firestoreData)


        } catch (e: Exception) {
            //  Log.e(TAG, "Error loading Data: $e")
        }
    }

    fun getAll(): LiveData<List<Rezept>> {
        return rezeptDataBase.dao.getAllItems()
    }

    suspend fun saveRezeptToFirestore(
        name: String,
        zutaten: String,
        zubereitung: String,
    ) {
        try {
            val rezeptId = UUID.randomUUID().toString()
            val localRezeptId = savePostAndGetIdLocally(rezeptId, name, zutaten, zubereitung)

            // Daten für das Rezept
            val rezeptInfo = Rezept(

                id = rezeptId,
                name = name,
                zubereitung = zubereitung,
                zutaten = zutaten
            )
            Log.d(
                "NeuesRezeptFragment",
                "Rezept erfolgreich in die Firebase-Datenbank gespeichert.${rezeptInfo}"
            )

            // Firebase-Referenz zur Sammlung "Rezepte" und Dokument mit eindeutiger ID
            firestore.collection("Rezepte").document(rezeptId).set(rezeptInfo)
            return localRezeptId

        } catch (e: Exception) {

            Log.e(TAG, "Error posting data: $e")

        }
    }


    private suspend fun savePostAndGetIdLocally(
        rezeptId: String,
        name: String,
        zutaten: String,
        zubereitung: String,


        ): Unit {
        val localRezept = Rezept(
            id = rezeptId,
            name = name,
            zubereitung = zubereitung,
            zutaten = zutaten
        )
        return savePostAndGetId(localRezept)
    }

    private suspend fun savePostAndGetId(localRezept: Rezept) {
        return withContext(Dispatchers.IO) {
            rezeptDataBase.dao.insertAndGetId(localRezept)
        }
    }
}

