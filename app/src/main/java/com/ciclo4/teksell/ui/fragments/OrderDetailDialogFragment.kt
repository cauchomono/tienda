package com.ciclo4.teksell.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import com.ciclo4.teksell.R
import com.ciclo4.teksell.model.ListaProductos
import com.ciclo4.teksell.model.Productos
import com.squareup.picasso.Picasso


class OrderFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_order_detail_dialog, container, false)

        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val producto = arguments?.getSerializable("product") as Productos

        val image = view.findViewById<ImageView>(R.id.imageV)
        val name = view.findViewById<TextView>(R.id.tvName)
        val price = view.findViewById<TextView>(R.id.tvPrice)
        val car = view.findViewById<TextView>(R.id.tvCaracteristica)


        car.text = producto.caracteristicas
        Picasso.get().load(producto.imagen).into(image)
        name.text = (producto.marca+": "+producto.modelo)
        price.text = ("$" + producto.precio)


        val btn = view.findViewById<Button>(R.id.btnAgregar)
        btn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.navHomeFragment)
            ListaProductos.addProductoToCarrito(producto)
        }
    }

}