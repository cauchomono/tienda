package com.ciclo4.teksell.network

import android.app.Activity
import android.net.Uri
import android.widget.Toast
import com.ciclo4.teksell.model.Usuarios
import com.ciclo4.teksell.ui.fragments.AdminDetailDialogFragment
import com.ciclo4.teksell.ui.fragments.AdminFragment
import com.google.android.play.core.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class FirestoreService {


    private val userDb =  FirebaseFirestore.getInstance()
    private val user = FirebaseAuth.getInstance().currentUser
    private val userEmail = user?.email.toString()

    val storage = Firebase.storage
    val storageRef = storage.reference

    val userPath = storageRef.child("${userEmail}")
    val profilePhoto = storageRef.child("${userEmail}/profilePhoto.jpg")


    fun getUsersDetail(callback: Callback<Usuarios>) {
          userDb.collection("users").document(userEmail).get()
              .addOnSuccessListener{result ->
                  val userDetails = result.toObject(Usuarios::class.java)
                  callback.OnSuccess(userDetails)
                  }
                  }

    fun updateUserDetail(callback: Callback<Usuarios>, map: Map<String,Any> ){

        userDb.collection("users").document(userEmail).set(map, SetOptions.merge())
            .addOnCompleteListener {
        }

    }

    fun updateProfilePhoto(callback: Callback<Usuarios>, uri: Uri) {

        val uploadTask = profilePhoto.putFile(uri)


    }



}

