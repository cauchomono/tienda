package com.ciclo4.teksell.model

import com.ciclo4.teksell.ui.fragments.CartFragment

class ListaProductos {

    companion object{

        var total:Double = 0.0
        var subtotal = 0.0
        var valor_iva = 0.0
        var iva = 0.16

        var cantidades =  mutableMapOf<Int, Int>()
        var productos :MutableList<Productos> = mutableListOf()

        fun delProductoToCarrito(producto:Productos){
            val precio = (producto.precio.toDouble() * -1 * cantidades.getValue(producto.hashCode()));
            calcularPrecio(precio)
        }

        fun addProductoToCarrito( producto:Productos){
            cantidades.put(producto.hashCode(), 0)
            println("Este es el Segundo HashCode"+ producto.hashCode())
            productos.add(producto)
            calcularPrecio(producto, 1)
        }

        fun calcularPrecio(producto:Productos, neg:Int){
            var precio = producto.precio.toDouble()*neg
            cantidades.put(producto.hashCode(), (cantidades.getValue(producto.hashCode())+neg))
            calcularPrecio(precio)
        }

        fun calcularPrecio(precio:Double){
            total += precio
            subtotal = Math.floor(total/(1+iva))
            valor_iva = total - subtotal
        }

    }

}