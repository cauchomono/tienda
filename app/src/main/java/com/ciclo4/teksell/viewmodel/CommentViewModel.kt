package com.ciclo4.teksell.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciclo4.teksell.model.Comment
import com.ciclo4.teksell.model.Usuarios
import com.ciclo4.teksell.network.Callback
import com.ciclo4.teksell.network.FirestoreService
import java.lang.Exception

class CommentViewModel: ViewModel() {
    val firestoreService = FirestoreService()
    var comments :MutableLiveData<List<Comment>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()


    fun refresh(){
        getCommentFromFirebase()
    }

    fun getCommentFromFirebase() {
        firestoreService.getComments(object : Callback<List<Comment>> {
            override fun OnSuccess(result: List<Comment>?) {
                comments.postValue(result!!)
                println("Resultados:" + comments)
                proccessFinished()
            }

            override fun OnFailed(exception: Exception) {
                proccessFinished()
            }
        })
    }

    fun proccessFinished() {
        isLoading.value = true
    }
}