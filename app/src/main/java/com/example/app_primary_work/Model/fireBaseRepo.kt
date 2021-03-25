package com.example.app_primary_work.Model

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class fireBaseRepo {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    //Auth
    fun getUser(): FirebaseUser?{
        return  firebaseAuth.currentUser
    }
    fun creareUser(): Task<AuthResult> {
        return firebaseAuth.signInAnonymously()
    }
    //FireBase
    fun getPostList(): Task<QuerySnapshot> {
        return firebaseFirestore.collection("Blog").orderBy("data", Query.Direction.DESCENDING).get()
    }
}