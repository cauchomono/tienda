package com.ciclo4.teksell.network

import android.app.Activity
import android.widget.Toast
import com.ciclo4.teksell.model.Usuario
import com.ciclo4.teksell.ui.fragments.AdminFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreService {


    private val userDb =  FirebaseFirestore.getInstance()
    private val user = FirebaseAuth.getInstance().currentUser
    private val userEmail = user?.email.toString()

    fun getUsersDetail(callback: Callback<Usuario>) {
          userDb.collection("users").get()
              .addOnSuccessListener{result ->
                  for (doc in result){
                      val list = result.toObjects(Usuario::class.java)
                      callback.OnSuccess(userDetails)
                  }

              }


        }
    }
