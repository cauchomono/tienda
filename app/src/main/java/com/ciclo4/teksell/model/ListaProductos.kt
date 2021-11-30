package com.ciclo4.teksell.model

class ListaProductos {

    companion object{
        lateinit var producto:Productos
        var total:Double = 0.0

        var productos:MutableList<Productos> = mutableListOf()

        fun addProductoToCarrito(){
            total += producto.precio
            productos.add(producto)
        }

    }

}