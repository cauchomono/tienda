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
import com.ciclo4.teksell.databinding.FragmentOrderDetailDialogBinding
import com.ciclo4.teksell.model.ListaProductos
import com.ciclo4.teksell.model.Productos
import com.squareup.picasso.Picasso


class OrderFragment : Fragment() {

    private var _binding: FragmentOrderDetailDialogBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderDetailDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val producto = arguments?.getSerializable("product") as Productos



        binding.tvCaracteristica.text = producto.caracteristicas
        Picasso.get().load(producto.imagen).into(binding.imageV)
        binding.tvName.text = (producto.marca+": "+producto.modelo)
        binding.tvPrice.text = ("$" + producto.precio)


        binding.btnAgregar.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.navHomeFragment)
            ListaProductos.addProductoToCarrito(producto)
        }
    }

}