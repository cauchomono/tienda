package com.ciclo4.teksell.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ciclo4.teksell.R
import com.ciclo4.teksell.model.ListaProductos
import com.ciclo4.teksell.model.Productos
import com.ciclo4.teksell.ui.fragments.OrderFragment
import com.squareup.picasso.Picasso

class ProductosAdapter (val productos:List<Productos>):RecyclerView.Adapter<ProductosAdapter.ProductosHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductosHolder(layoutInflater.inflate(R.layout.item_product,parent, false))
    }

    override fun onBindViewHolder(holder: ProductosHolder, position: Int) {
        holder.render(productos[position])
    }

    override fun getItemCount(): Int {
        return productos.size
    }


    class ProductosHolder(val view: View):RecyclerView.ViewHolder(view) {
        val ivImagenProducto : ImageView = view.findViewById(R.id.ivImagenProducto)
        val tvNombreProducto : TextView = view.findViewById(R.id.tvNombreProducto)
        val tvPrecioProducto : TextView = view.findViewById(R.id.tvPrecioProducto)

        fun render(productos: Productos){

            tvNombreProducto.text = productos.marca + productos.modelo
            tvPrecioProducto.text = "$"+productos.precio.toString()
            Picasso.get().load(productos.image).into(ivImagenProducto)
            view.setOnClickListener {
                ListaProductos.producto = productos
                Navigation.findNavController(view).navigate(R.id.navOrderDetailFragmentDialog)
                //findNavController().navigate(R.id.navOrderDetailFragmentDialog)
            }

        }
    }



}