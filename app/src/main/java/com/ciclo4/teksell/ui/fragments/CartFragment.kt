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
import com.ciclo4.teksell.databinding.FragmentCartBinding
import com.ciclo4.teksell.view.adapter.ProductosInCartAdapter
import com.ciclo4.teksell.model.ListaProductos


class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val view = binding.root
        initRecycler(view)
        return view
    }

    private fun initRecycler(view:View) {
        val  rvCart: RecyclerView = view.findViewById(R.id.rvCart)
        val adapter = ProductosInCartAdapter(ListaProductos.productos)
        rvCart.layoutManager = LinearLayoutManager(this.context)
        rvCart.adapter = adapter
        binding.totalTv.text ="Precio total: $"+ ListaProductos.total
        binding.subtotalTv.text = "Subtotal: $"+ ListaProductos.subtotal
        binding.valorIvaTv.text = "Valor iva: $"+ ListaProductos.valor_iva
    }


}