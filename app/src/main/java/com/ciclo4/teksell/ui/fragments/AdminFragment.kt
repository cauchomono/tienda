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
import com.ciclo4.teksell.databinding.FragmentAdminBinding
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

    private var _binding : FragmentAdminBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdminBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firestoreService = FirestoreService()
        val uri = Uri.parse("android.resource://com.ciclo4.teksell/" + R.drawable.logo)

        val profileVi = view?.findViewById<ImageView>(R.id.profileVi)
        profileVi?.setImageURI(uri)


        firestoreService.profilePhoto.downloadUrl.addOnSuccessListener {


            Picasso.get().load(it).into(binding.profileVi)

        }.addOnFailureListener {

            Toast.makeText(this.context,"No se cargo la imagen de perfil. Si es la primera vez, configurela en editar",
                Toast.LENGTH_LONG).show()

        }


    }

    override fun onStart() {
        super.onStart()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
        usuarioViewModel.getUserFromFirebase()



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
        observeViewModel()

    }
    fun observeViewModel(){


        usuarioViewModel.usuarios.observe(viewLifecycleOwner, Observer<Usuarios>{ usuarios ->
            binding.userEmailVt?.text = usuarios.email
            binding.userNameVt?.text =usuarios.name
            binding.userDirectionVt?.text =usuarios.address
            binding.userPhoneVt?.text =usuarios.contact
        })
    }



}