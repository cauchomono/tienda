package com.ciclo4.teksell.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciclo4.teksell.R
import com.ciclo4.teksell.databinding.FragmentHomeBinding
import com.ciclo4.teksell.model.ListaProductos.Companion.productos
import com.ciclo4.teksell.view.adapter.ProductosAdapter
import com.ciclo4.teksell.model.Productos
import com.ciclo4.teksell.view.adapter.ProductsListener
import com.ciclo4.teksell.viewmodel.ProductosViewModel
import com.ciclo4.teksell.viewmodel.UsuarioViewModel


class HomeFragment : Fragment(), ProductsListener {

    private lateinit var productosViewModel  : ProductosViewModel
    private lateinit var productsAdapter: ProductosAdapter

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val usuarioViewModel = UsuarioViewModel()
        usuarioViewModel.getUserFromFirebase()

        productosViewModel = ViewModelProvider(this).get(ProductosViewModel::class.java)
        productosViewModel.getProductsFromFirebase()


        productsAdapter =  ProductosAdapter(this)

        val  rvHome: RecyclerView = view.findViewById(R.id.rvHome)
        rvHome.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = productsAdapter
        }
        observeViewModel()

    }

    fun observeViewModel(){
        productosViewModel.listProducts.observe(viewLifecycleOwner, Observer<List<Productos>> {
            products ->
            productsAdapter.updateData(products)
        })

        productosViewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {

            if(it != null)
                view?.findViewById<ProgressBar>(R.id.loadingPb)?.visibility = View.INVISIBLE
        })
    }

    override fun OnProductsClick(product: Productos, position: Int) {
        val bundle = bundleOf("product" to product)
        findNavController().navigate(R.id.navOrderDetailFragmentDialog,bundle)
    }

}
