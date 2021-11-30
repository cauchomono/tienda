package com.ciclo4.teksell.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ciclo4.teksell.R
import com.ciclo4.teksell.model.Usuario
import com.ciclo4.teksell.ui.activities.InitialActivity
import com.ciclo4.teksell.viewmodel.UsuarioViewModel
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class AdminFragment : Fragment() {



    private lateinit var usuarioViewModel  : UsuarioViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        val userNameVt = view?.findViewById<TextView>(R.id.userNameVt)
        val userDirectionVt = view?.findViewById<TextView>(R.id.userDirectionVt)
        val userPhoneVt = view?.findViewById<TextView>(R.id.userPhoneVt)
        val userEmailVt = view?.findViewById<TextView>(R.id.userEmailVt )



        usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)

        usuarioViewModel.refresh()


        val usuario = arguments?.getSerializable("users") as Usuario

        userEmailVt?.text = usuario.email
        userNameVt?.text =usuario.name
        userDirectionVt?.text =usuario.address
        userPhoneVt?.text = usuario.phone



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




}