package com.ciclo4.teksell.ui.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.ciclo4.teksell.R
import com.ciclo4.teksell.databinding.FragmentLoginBinding
import com.ciclo4.teksell.model.Usuarios
import com.ciclo4.teksell.ui.activities.MainActivity
import com.ciclo4.teksell.viewmodel.UsuarioViewModel
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.longrunning.WaitOperationRequest
import kotlinx.coroutines.android.awaitFrame


class LoginFragment : Fragment() {

    private lateinit var usuarioViewModel  : UsuarioViewModel

    val AUTH_REQUEST_CODE = 1234
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var listener: FirebaseAuth.AuthStateListener
    lateinit var providers: List<AuthUI.IdpConfig>

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this.context, MainActivity::class.java)
        usuarioViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
        firebaseAuth = FirebaseAuth.getInstance()
        listener = FirebaseAuth.AuthStateListener { p0 ->
            val user = p0.currentUser

        providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
        )
            if (user != null) {
                startActivity(intent)
            }else{
                binding.otherBtn?.setOnClickListener {
                    startActivityForResult(
                        AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setAlwaysShowSignInMethodScreen(true)
                            .build(), AUTH_REQUEST_CODE
                    )
            }

                binding.enterBtn?.setOnClickListener {
                view?.let { it1 -> existUser(it1) }
            }
        }}

    }



    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(listener)

    }

    override fun onStop() {
        if (listener != null)
            firebaseAuth.removeAuthStateListener(listener)
        super.onStop()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun existUser(view: View) {
        val email = view.findViewById<EditText>(R.id.emailEt).text.toString()
        val pass = view.findViewById<EditText>(R.id.passwordEt).text.toString()

        if (email != null && email != "" &&  pass != null &&  pass != "") {
            firebaseAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener {
                    if (it.isSuccessful) {

                        val intento1 = Intent(this.context, MainActivity::class.java)

                        startActivity(intento1)

                    } else {
                        showAlert("Fracaso en el logueo", "Vuelva a intentarlo")
                    }
                }

        }else{
            showAlert("Faltan datos", "Ingrese email y/o contraseña")
        }
    }
    fun showAlert(alert:String, message:String){
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }
        builder?.setTitle(alert)
        builder?.setMessage(message)
        builder?.show()
    }

//    val NODATA = true
//    fun loginWithoutData(){
//        val intento1 = Intent(this.context, MainActivity::class.java)
//        startActivity(intento1)
//    }

}