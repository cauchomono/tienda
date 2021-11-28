package com.ciclo4.teksell.model

class ListaProductos {

    companion object{
        lateinit var producto:Productos

        var productos:MutableList<Productos> = mutableListOf()

        fun addProductoToCarrito(){
            productos.add(producto)
        }

    }

}