package com.ciclo4.teksell.network

import com.ciclo4.teksell.adapter.CommentAdapter
import com.ciclo4.teksell.model.Comment
import com.ciclo4.teksell.model.Productos
import com.ciclo4.teksell.model.Usuarios
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

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

    fun updateUserDetail(callback: Callback<Usuarios>, map: Map<String,Any> ){
        userDb.collection("users").document(userEmail).set(map, SetOptions.merge())
            .addOnCompleteListener {
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

