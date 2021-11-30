package com.ciclo4.teksell.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.ciclo4.teksell.R
import com.ciclo4.teksell.ui.activities.InitialActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth


class AdminFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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