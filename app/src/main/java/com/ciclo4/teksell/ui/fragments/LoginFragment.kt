package com.ciclo4.teksell.ui.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.ciclo4.teksell.R
import com.ciclo4.teksell.model.ListaUsuarios
import com.ciclo4.teksell.model.Usuario
import com.ciclo4.teksell.ui.activities.MainActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val enterBtn = view.findViewById<Button>(R.id.enterBtn)
       enterBtn.setOnClickListener {
            if(existUser(view)){
                val intento1  = Intent(this.context, MainActivity::class.java)
                startActivity(intento1)
            }
        }
    }

    fun existUser(view: View):Boolean{
        val user = view.findViewById<EditText>(R.id.usernameEt).text.toString()
        val pass = view.findViewById<EditText>(R.id.passwordEt).text.toString()
        val usuario :Usuario? = ListaUsuarios.getUser(user)
        if(usuario != null){
            if(usuario.password == pass){
                return true
            }
            showAlert("Contraseña incorrecta", "Vuelve a ingresar la contraseña...")
        }
        else{
            showAlert("Usuario incorrecto", "No existe el usuario "+user)
        }
        return false
    }

    fun showAlert(alert:String, message:String){
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }
        builder?.setTitle(alert)
        builder?.setMessage(message)
        builder?.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}