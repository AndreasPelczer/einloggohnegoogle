package com.example.einloggohnegoogle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FirebaseViewmodel(application: Application) : AndroidViewModel(application) {


    val firestore = FirebaseFirestore.getInstance()
    private val rezeptDatabase = getRezeptDatabase(application)

    private val repository = FirebaseRepository(rezeptDatabase, firestore)
    val firebaseAuth = FirebaseAuth.getInstance()

    val rezeptDataList: LiveData<List<Rezept>> = repository.getAll()
    val _user: MutableLiveData<FirebaseUser?> = MutableLiveData()
    val user: LiveData<FirebaseUser?>
        get() = _user

    lateinit var profileRef: DocumentReference


    fun loadfromFireStore() {

        viewModelScope.launch(Dispatchers.IO) {
            val localData = repository.getAll().value
            if (localData.isNullOrEmpty()) {
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
            val profile = Profile("User", extra)
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

}

