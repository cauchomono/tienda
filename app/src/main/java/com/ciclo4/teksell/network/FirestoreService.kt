package com.ciclo4.teksell.network

import android.app.Activity
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.ciclo4.teksell.model.Comment
import com.ciclo4.teksell.model.Productos
import com.ciclo4.teksell.model.Usuarios
import com.ciclo4.teksell.ui.fragments.AdminDetailDialogFragment
import com.ciclo4.teksell.ui.fragments.AdminFragment
import com.google.android.play.core.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.File

class FirestoreService {


     val userDb =  FirebaseFirestore.getInstance()
     val user = FirebaseAuth.getInstance().currentUser
     val userEmail = user?.email.toString()

    val storage = Firebase.storage
    val storageRef = storage.reference

    val userPath = storageRef.child("${userEmail}")
    val profilePhoto = storageRef.child("${userEmail}/profilePhoto.jpg")


    fun newUserFromProviders(callback: Callback<Usuarios>){
        var userRegistered = hashMapOf(
            "name" to "Sin nombre",
            "user" to "Sin usuario",
            "email" to userEmail,
            "address" to "sin dirección",
            "contact" to "Sin número celular")

        newUser(callback, userRegistered, userEmail)

    }


    fun newUser(callback: Callback<Usuarios>, map: Map<String,Any>, newUserEmail : String){
        userDb.collection("users").document(newUserEmail).set(map)

        val uri = Uri.fromFile(File("app/src/main/res/drawable-v24/logo.jpg"))
        storageRef.child("${newUserEmail}/profilePhoto.jpg").putFile(uri)


    }

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


    fun getProducts(callback: Callback<List<Productos>>){
        userDb.collection("productos").get().addOnSuccessListener { result ->
                for(doc in result){
                    val list = result.toObjects(Productos::class.java)
                    callback.OnSuccess(list)
                    break
                }
            }
    }

    fun uploadComments(callback: Callback<Comment>, map: Map<String,Any>){
        userDb.collection("comentarios").document(userEmail).set(map).addOnSuccessListener {

        }
    }

    fun getComments(callback: Callback<List<Comment>>) {
        userDb.collection("comments")
            .orderBy("score")
            .get()
            .addOnSuccessListener { result->
                for (doc in result){
                    val list = result.toObjects(Comment::class.java)
                    callback.OnSuccess(list)
                    break
                }
            }
    }

    fun addMessage(comment: Comment){

        userDb.collection("comments").add(comment)

    }


}

