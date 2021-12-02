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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ciclo4.teksell.R
import com.ciclo4.teksell.network.FirestoreService
import com.ciclo4.teksell.viewmodel.UsuarioViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import io.grpc.Context


class AdminDetailDialogFragment : Fragment() {


    lateinit var usuarioViewModel: UsuarioViewModel

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri ->
        val idPhotoAdmin = view?.findViewById<ImageButton>(R.id.idPhotoAdmin)
        idPhotoAdmin?.setImageURI(uri)
        usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
        usuarioViewModel.updatePhotoProfile(uri)


    }


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



        val firestoreService = FirestoreService()

        val idPhotoAdmin = view?.findViewById<ImageButton>(R.id.idPhotoAdmin)

        firestoreService.profilePhoto.downloadUrl.addOnSuccessListener {
            Picasso.get().load(it).into(idPhotoAdmin)

        }.addOnFailureListener {
            Toast.makeText(this.context,"No se pudo cargar la imagen",Toast.LENGTH_LONG).show()
        }




        idPhotoAdmin?.setOnClickListener {
            val firestoreService = FirestoreService()
            getContent.launch("image/*")

        }
        val btnAdmin = view?.findViewById<Button>(R.id.btnAdmin)

        btnAdmin?.setOnClickListener {

            val etNombreAdmin = view?.findViewById<EditText>(R.id.etNombreAdmin)
            val etDireccionAdmin = view?.findViewById<EditText>(R.id.etDireccionAdmin)
            val etTelefonoAdmin = view?.findViewById<EditText>(R.id.etTelefonoAdmin)



            val settingsMap : Map<String,Any> = mapOf(
                Pair("name",etNombreAdmin?.text.toString()),
                Pair("contact",etTelefonoAdmin?.text.toString()),
                Pair("address",etDireccionAdmin?.text.toString()),

            )



            usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
            usuarioViewModel.updateUserInFirebase(settingsMap)

            findNavController().navigate(R.id.navAdminFragment)
        }


    }

}