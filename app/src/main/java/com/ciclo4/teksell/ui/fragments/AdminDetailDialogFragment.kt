package com.ciclo4.teksell.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ciclo4.teksell.R
import com.ciclo4.teksell.viewmodel.UsuarioViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AdminDetailDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminDetailDialogFragment : Fragment() {
    lateinit var usuarioViewModel: UsuarioViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_detail_dialog, container, false)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAdmin = view?.findViewById<Button>(R.id.btnAdmin)

        val idPhotoAdmin = view?.findViewById<ImageButton>(R.id.idPhotoAdmin)


        btnAdmin?.setOnClickListener {

            val etNombreAdmin = view?.findViewById<EditText>(R.id.etNombreAdmin)
            val etDireccionAdmin = view?.findViewById<EditText>(R.id.etDireccionAdmin)
            val etTelefonoAdmin = view?.findViewById<EditText>(R.id.etTelefonoAdmin)



            val settingsMap : Map<String,Any> = mapOf(
                Pair("name",etNombreAdmin?.text.toString()),
                Pair("contact",etTelefonoAdmin?.text.toString()),
                Pair("address",etDireccionAdmin?.text.toString())
            )

            usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
            usuarioViewModel.updateUserInFirebase(settingsMap)
            Toast.makeText(this.context,"Se Guardo",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.navAdminFragment)
        }

    }
}