package com.ciclo4.teksell.view.adapter

import com.ciclo4.teksell.model.Productos
import java.text.FieldPosition

interface ProductsListener {
    fun OnProductsClick(product : Productos,position: Int)
}