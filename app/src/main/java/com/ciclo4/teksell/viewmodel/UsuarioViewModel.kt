package com.ciclo4.teksell.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciclo4.teksell.model.Usuario
import com.ciclo4.teksell.network.Callback
import com.ciclo4.teksell.network.FirestoreService
import java.lang.Exception

class UsuarioViewModel: ViewModel() {
    val firestoreService = FirestoreService()
    var usuario = MutableLiveData<Usuario>()
    var isLoading = MutableLiveData<Boolean>()


    fun refresh(){
        getUserFromFirebase()
    }

     fun getUserFromFirebase() {

         firestoreService.getUsersDetail(object : Callback<Usuario> {
             override fun OnSuccess(result: Usuario?) {
                 usuario.postValue(result!!)
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