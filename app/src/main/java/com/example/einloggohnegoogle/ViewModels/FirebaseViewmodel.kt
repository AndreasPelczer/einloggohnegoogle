package com.example.einloggohnegoogle.ViewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.einloggohnegoogle.repository.FirebaseRepository
import com.example.einloggohnegoogle.data.datamodels.Profile
import com.example.einloggohnegoogle.data.datamodels.Rezept
import com.example.einloggohnegoogle.data.database.getRezeptDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FirebaseViewmodel(application: Application) : AndroidViewModel(application) {


    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val firestore = FirebaseFirestore.getInstance()
    private val rezeptDatabase = getRezeptDatabase(application)

    private val repository = FirebaseRepository(rezeptDatabase, firestore, auth )
    val firebaseAuth = FirebaseAuth.getInstance()

    val rezeptDataList: LiveData<List<Rezept>> = repository.getAll()
    val _user: MutableLiveData<FirebaseUser?> = MutableLiveData()
    val user: LiveData<FirebaseUser?>
        get() = _user

    private val _rezeptdetail = MutableLiveData<Rezept>()
    val rezeptdetail: MutableLiveData<Rezept>
        get() = _rezeptdetail

    lateinit var profileRef: DocumentReference

    private val database = FirebaseDatabase.getInstance()

    fun getCurrentUserId(): String? {
        return repository.getCurrentUserId()
    }
    fun insertRezeptData(itemData: Rezept) {
        viewModelScope.launch {
            try {
                val userId = getCurrentUserId()

                if (userId != null) {
                    Log.e("RezeptViewModel", "Error inserting data: ${itemData.name}")
                    repository.saveRezeptToFirestore(itemData.name, itemData.zubereitung,itemData.zutaten,itemData.videoupload)
                }
            } catch (e: Exception) {
                Log.e("Rezept1ViewModel", "Error inserting data: $e")
            }
        }
    }


    fun loadfromFireStore() {

        Log.d("FirebaseLoad", "Attempting to load data from Firebase")

        viewModelScope.launch(Dispatchers.IO) {
            val localData = repository.getAll().value
            if (localData.isNullOrEmpty()) {
                Log.d("FirebaseLoad", "load data from Firebase")

                // Es gibt keine Daten in der Room-Datenbank, lade Daten aus Firestore.//
                repository.getRezeptAndSaveToDatabase()
            }
        }
    }
    init {
        setupUserEnv()
    }

    //Wird aufgerufen wenn der User eingeloggt wurde
    fun setupUserEnv() {
        _user.value = firebaseAuth.currentUser

        //if(user != null)
        firebaseAuth.currentUser?.let {
            //Lege Profildaten an
            profileRef = firestore.collection("Profile").document(firebaseAuth.currentUser!!.uid)
        }
    }

    fun signUp(email: String, password: String, extra: String = "") {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            //Wenn Task fertig ist dann überprüfe z.B. ob der User eingeloggt wurde
            //oder ob es Fehler gab o.Ä.
            setupUserEnv()
            val profile = Profile(
                "User",
                extra,
                "",
                )
            profileRef.set(profile)
        }
    }

    fun signOut() {
        firebaseAuth.signOut()
        _user.value = firebaseAuth.currentUser
    }

    fun signIn(email: String, password: String) {

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            //Wenn Task fertig ist dann überprüfe z.B. ob der User eingeloggt wurde
            //oder ob es Fehler gab o.Ä.

            setupUserEnv()
        }
    }
    fun loadRezeptDetail(id: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val detail = repository.getRezeptDetail(id!!)
                rezeptdetail.postValue(detail)
            } catch (e: Exception) {
                Log.e("PostsViewModel", "Error loading post detail: $e")
            }
        }
    }

}



