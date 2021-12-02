package com.ciclo4.teksell.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciclo4.teksell.model.Productos
import com.ciclo4.teksell.network.Callback
import com.ciclo4.teksell.network.FirestoreService
import java.lang.Exception

class ProductosViewModel: ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    val firestoreService = FirestoreService()
    var listProducts = MutableLiveData<List<Productos>>()

    fun getProductsFromFirebase(){
        firestoreService.getProducts(object : Callback<List<Productos>> {
            override fun OnSuccess(result: List<Productos>?) {
                listProducts.postValue(result!!)
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