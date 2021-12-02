package com.ciclo4.teksell.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciclo4.teksell.R
import com.ciclo4.teksell.view.adapter.ProductosInCartAdapter
import com.ciclo4.teksell.model.ListaProductos


class CartFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        initRecycler(view)
        return view
    }

    private fun initRecycler(view:View) {
        val  rvCart: RecyclerView = view.findViewById(R.id.rvCart)
        val adapter = ProductosInCartAdapter(ListaProductos.productos)
        rvCart.layoutManager = LinearLayoutManager(this.context)
        rvCart.adapter = adapter
        view.findViewById<TextView>(R.id.totalTv).text ="Precio total: $"+ ListaProductos.total
        view.findViewById<TextView>(R.id.subtotalTv).text = "Subtotal: $"+ ListaProductos.subtotal
        view.findViewById<TextView>(R.id.valorIvaTv).text = "Valor iva: $"+ ListaProductos.valor_iva
    }


}