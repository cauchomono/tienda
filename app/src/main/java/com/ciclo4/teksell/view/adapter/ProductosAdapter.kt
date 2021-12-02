package com.ciclo4.teksell.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ciclo4.teksell.R
import com.ciclo4.teksell.model.ListaProductos
import com.ciclo4.teksell.model.Productos
import com.squareup.picasso.Picasso

class ProductosAdapter (val productsListener : ProductsListener):
    RecyclerView.Adapter<ProductosAdapter.ViewHolder>(){

    var listProducts = ArrayList<Productos>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false
        ))


    override fun onBindViewHolder(holder: ProductosAdapter.ViewHolder, position: Int) {
        val productos = listProducts[position]
        holder.tvNombreProducto.text = productos.marca + productos.modelo
        holder.tvPrecioProducto.text = "$"+productos.precio
        Picasso.get().load(productos.imagen).into(holder.ivImagenProducto)

        holder.view.setOnClickListener {
            productsListener.OnProductsClick(productos,position)
        }
    }

    override fun getItemCount(): Int {
        return listProducts.size
    }

    fun updateData(data: List<Productos>){
        listProducts.clear()
        listProducts.addAll(data)
        notifyDataSetChanged()
    }


    class ViewHolder(val view: View):RecyclerView.ViewHolder(view) {
        val ivImagenProducto : ImageView = view.findViewById(R.id.ivImagenProducto)
        val tvNombreProducto : TextView = view.findViewById(R.id.tvNombreProducto)
        val tvPrecioProducto : TextView = view.findViewById(R.id.tvPrecioProducto)

    }



}