package com.ciclo4.teksell.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciclo4.teksell.model.Comment
import com.ciclo4.teksell.model.Productos
import com.ciclo4.teksell.network.Callback
import com.ciclo4.teksell.network.FirestoreService
import java.lang.Exception

class CommentViewModel: ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    val firestoreService = FirestoreService()
    var listComments = MutableLiveData<List<Comment>>()

    fun getCommentsFromFirebase(){
        firestoreService.getComments(object : Callback<List<Comment>> {
            override fun OnSuccess(result: List<Comment>?) {
                listComments.postValue(result!!)
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