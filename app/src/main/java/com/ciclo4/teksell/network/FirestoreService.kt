package com.ciclo4.teksell.network

import android.app.Activity
import android.widget.Toast
import com.ciclo4.teksell.model.Usuarios
import com.ciclo4.teksell.ui.fragments.AdminFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreService {


    private val userDb =  FirebaseFirestore.getInstance()
    private val user = FirebaseAuth.getInstance().currentUser
    private val userEmail = user?.email.toString()


    fun getUsersDetail(callback: Callback<Usuarios>) {
          userDb.collection("users").document(userEmail).get()
              .addOnSuccessListener{result ->
                  val userDetails = result.toObject(Usuarios::class.java)
                  callback.OnSuccess(userDetails)

                  }

                  }

              }



