package com.ciclo4.teksell.model

class ListaProductos {

    companion object{

        var total:Double = 0.0
        var subtotal = 0.0
        var valor_iva = 0.0
        var iva = 0.16

        var productos:MutableList<Productos> = mutableListOf()

        fun addProductoToCarrito( producto:Productos){
            total += producto.precio.toDouble()
            subtotal = Math.floor(total/(1+iva))
            valor_iva = total - subtotal
            productos.add(producto)
        }

    }

}