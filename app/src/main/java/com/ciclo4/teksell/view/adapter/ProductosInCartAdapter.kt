package com.ciclo4.teksell.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ciclo4.teksell.R
import com.ciclo4.teksell.model.Comment
import com.ciclo4.teksell.model.ListaProductos
import com.ciclo4.teksell.model.Productos
import com.ciclo4.teksell.ui.fragments.CartFragment
import com.squareup.picasso.Picasso

class ProductosInCartAdapter (val productos:MutableList<Productos>):RecyclerView.Adapter<ProductosInCartAdapter.ProductosHolder>(){

    lateinit var btnDelete:ImageView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_product_in_cart,parent, false)
        btnDelete=view.findViewById(R.id.imageViewDeleteP)
        return ProductosHolder(view)
    }

    override fun onBindViewHolder(holder: ProductosHolder, position: Int) {
        btnDelete.setOnClickListener {
            ListaProductos.delProductoToCarrito(productos[position])
            delData(productos[position])
            CartFragment.actualizarDatos()
        }
        holder.render(productos[position])
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    fun delData(producto: Productos){
        productos.remove(producto)
        notifyDataSetChanged()
    }

    class ProductosHolder(val view: View):RecyclerView.ViewHolder(view) {
        val ivImagenProducto : ImageView = view.findViewById(R.id.ivImagenProducto)
        val tvNombreProducto : TextView = view.findViewById(R.id.tvNombreProducto)
        val tvPrecioProducto : TextView = view.findViewById(R.id.tvPrecioProducto)
        val etns:EditText = view.findViewById(R.id.editTextNumberSigned)
        val llproduct : LinearLayout = view.findViewById(R.id.llProducto)
        val btnMenos: Button = view.findViewById(R.id.buttonMenos)
        val btnMas: Button = view.findViewById(R.id.buttonMas)
        val btnDelete :ImageView=view.findViewById(R.id.imageViewDeleteP)

        fun render(producto: Productos){

            tvNombreProducto.text = producto.marca + producto.modelo
            tvPrecioProducto.text = "$"+producto.precio
            println("Este es el tercer HashCode"+ producto.hashCode())
            etns.setText(""+ListaProductos.cantidades.get(producto.hashCode()))
            Picasso.get().load(producto.imagen).into(ivImagenProducto)
            llproduct.setOnClickListener {
                //Navigation.findNavController(view).navigate(R.id.navOrderDetailFragmentDialog)
            }
            btnMenos.setOnClickListener{

                var cant :Int = etns.text.toString().toInt()
                if(cant-->1){
                    etns.setText(""+cant)
                    ListaProductos.calcularPrecio(producto, -1)
                    CartFragment.actualizarDatos()
                }
            }
            btnMas.setOnClickListener{
                var cant :Int = etns.text.toString().toInt()
                cant++
                etns.setText(""+cant)
                ListaProductos.calcularPrecio(producto, 1)
                CartFragment.actualizarDatos()
            }

        }
    }



}