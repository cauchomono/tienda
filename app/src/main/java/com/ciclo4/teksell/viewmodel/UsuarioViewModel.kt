package com.ciclo4.teksell.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciclo4.teksell.model.Productos
import com.ciclo4.teksell.model.Usuarios
import com.ciclo4.teksell.network.Callback
import com.ciclo4.teksell.network.FirestoreService
import java.lang.Exception

class UsuarioViewModel: ViewModel() {
    val firestoreService = FirestoreService()
    var usuarios = MutableLiveData<Usuarios>()
    var isLoading = MutableLiveData<Boolean>()



    fun refresh(){
        getUserFromFirebase()
    }

    fun createUserFirebaseFromProviders(){
        firestoreService.newUserFromProviders(object : Callback<Usuarios> {
            override fun OnSuccess(result: Usuarios?) {
                proccessFinished()
            }

            override fun OnFailed(exception: Exception) {
                proccessFinished()
            }
        })
    }

    fun createUserFirebase(map: Map<String,Any> , email :  String){
        firestoreService.newUser(object : Callback<Usuarios> {
            override fun OnSuccess(result: Usuarios?) {
                proccessFinished()
            }

            override fun OnFailed(exception: Exception) {
                proccessFinished()
            }
        }, map, email)
    }

     fun getUserFromFirebase() {
         firestoreService.getUsersDetail(object : Callback<Usuarios> {
             override fun OnSuccess(result: Usuarios?) {
                 if(result == null){
                     createUserFirebaseFromProviders()
                     proccessFinished()
                 }else{
                 usuarios.postValue(result!!)
                 proccessFinished()}}


             override fun OnFailed(exception: Exception) {
                 proccessFinished()
             }
         })
     }

    fun updateUserInFirebase(map: Map<String,Any>){
        firestoreService.updateUserDetail(object : Callback<Usuarios>{
            override fun OnSuccess(result: Usuarios?) {
                proccessFinished()
            }
            override fun OnFailed(exception: Exception) {
                proccessFinished()
            }
        }, map)

    }

    fun updatePhotoProfile(uri: Uri){
        firestoreService.updateProfilePhoto(object : Callback<Usuarios>{
            override fun OnSuccess(result: Usuarios?) {
                proccessFinished()
            }
            override fun OnFailed(exception: Exception) {
                proccessFinished()
            }
        }, uri)
    }



    fun proccessFinished() {
        isLoading.value = true
    }
}