package com.ciclo4.teksell.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciclo4.teksell.R
import com.ciclo4.teksell.adapter.ProductosAdapter
import com.ciclo4.teksell.model.Productos

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    val productos = listOf(
        Productos("Samsung","S21","Pantalla: 6,7 pulgadas, Resolución: 2.400 x 1.080 píxeles",1000.0,"https://m.media-amazon.com/images/I/81J0QOSKU-L._AC_SX425_.jpg"),
        Productos("Motorola","G20","Pantalla: 6,7 pulgadas, Resolución: 2.400 x 1.080 píxeles",1000.0,"https://tigocolombia.vteximg.com.br/arquivos/ids/159947-1000-1000/G20_Azul_1.png?v=637679410118370000"),
        Productos("Iphone","10","Pantalla: 6,7 pulgadas, Resolución: 2.400 x 1.080 píxeles",1000.0,"https://m.media-amazon.com/images/I/61HHOHgYb1L._AC_SL1500_.jpg")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    private fun initRecycler(view:View) {
      val  rvHome: RecyclerView = view.findViewById(R.id.rvHome)
        val adapter = ProductosAdapter(productos)
        rvHome.layoutManager = LinearLayoutManager(this.context)
        rvHome.adapter = adapter

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_home, container, false)
        initRecycler(view)
        return view
    }


    }
