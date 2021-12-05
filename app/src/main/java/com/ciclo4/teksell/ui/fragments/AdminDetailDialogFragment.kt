package com.ciclo4.teksell.ui.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ciclo4.teksell.R
import com.ciclo4.teksell.databinding.FragmentAdminDetailDialogBinding
import com.ciclo4.teksell.model.Usuarios
import com.ciclo4.teksell.network.FirestoreService
import com.ciclo4.teksell.viewmodel.UsuarioViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import io.grpc.Context


class AdminDetailDialogFragment : Fragment() {


    lateinit var usuarioViewModel: UsuarioViewModel
    private var _binding: FragmentAdminDetailDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminDetailDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri ->

        binding.idPhotoAdmin?.setImageURI(uri)
        usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
        usuarioViewModel.updatePhotoProfile(uri)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
        usuarioViewModel.getUserFromFirebase()

        val firestoreService = FirestoreService()

        firestoreService.profilePhoto.downloadUrl.addOnSuccessListener {
            Picasso.get().load(it).into(binding.idPhotoAdmin)
        }.addOnFailureListener {
            Toast.makeText(this.context,"No se pudo cargar la imagen",Toast.LENGTH_LONG).show()
        }

        binding.idPhotoAdmin?.setOnClickListener {

            getContent.launch("image/*")

        }

        val btnAdmin = view?.findViewById<Button>(R.id.btnAdmin)

        btnAdmin?.setOnClickListener {

            val settingsMap : Map<String,Any> = mapOf(
                Pair("name",binding.etNombreAdmin?.text.toString()),
                Pair("contact",binding.etTelefonoAdmin?.text.toString()),
                Pair("address",binding.etDireccionAdmin?.text.toString()),
            )
            usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
            usuarioViewModel.updateUserInFirebase(settingsMap)

            findNavController().navigate(R.id.navAdminFragment)
        }

        observeViewModel()
    }

    fun observeViewModel(){

        usuarioViewModel.usuarios.observe(viewLifecycleOwner, Observer<Usuarios>{ usuarios ->

            binding.etNombreAdmin?.setText(usuarios.name)
            binding.etDireccionAdmin?.setText(usuarios.address)
            binding.etTelefonoAdmin?.setText(usuarios.contact)
        })
    }

}