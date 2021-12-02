package com.ciclo4.teksell.ui.fragments

import android.content.ContentResolver
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentResolverCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ciclo4.teksell.R
import com.ciclo4.teksell.model.Usuarios
import com.ciclo4.teksell.network.FirestoreService
import com.ciclo4.teksell.ui.activities.InitialActivity
import com.ciclo4.teksell.viewmodel.UsuarioViewModel
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import java.io.File


class AdminFragment : Fragment() {

    private lateinit var usuarioViewModel  : UsuarioViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firestoreService = FirestoreService()
        val uri = Uri.parse("android.resource://com.ciclo4.teksell/" + R.drawable.logo)
        val profileVi = view?.findViewById<ImageView>(R.id.profileVi)
        profileVi?.setImageURI(uri)


        firestoreService.profilePhoto.downloadUrl.addOnSuccessListener {
            val profileVi = view?.findViewById<ImageView>(R.id.profileVi)

            Picasso.get().load(it).into(profileVi)


        }.addOnFailureListener {


            Toast.makeText(this.context,"No se pudo cargar la imagen", Toast.LENGTH_LONG).show()

        }


    }

    override fun onStart() {
        super.onStart()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
        usuarioViewModel.getUserFromFirebase()


        observeViewModel()
        val editBtn = view.findViewById<Button>(R.id.editBtn)
        editBtn.setOnClickListener {
            findNavController().navigate(R.id.adminDetailFragmentDialog)
        }

        val exitBtn = view.findViewById<Button>(R.id.exitBtn)

        exitBtn.setOnClickListener {
            AuthUI.getInstance()
                .signOut(view.context)
                .addOnCompleteListener {
                    val intent = Intent(this.context, InitialActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
        }
    }
    fun observeViewModel(){
        val userNameVt = view?.findViewById<TextView>(R.id.userNameVt)
        val userDirectionVt = view?.findViewById<TextView>(R.id.userDirectionVt)
        val userPhoneVt = view?.findViewById<TextView>(R.id.userPhoneVt)
        val userEmailVt = view?.findViewById<TextView>(R.id.userEmailVt )

        usuarioViewModel.usuarios.observe(viewLifecycleOwner, Observer<Usuarios>{ usuarios ->
            userEmailVt?.text = usuarios.email
            userNameVt?.text =usuarios.name
            userDirectionVt?.text =usuarios.address
            userPhoneVt?.text =usuarios.contact
        })
    }



}