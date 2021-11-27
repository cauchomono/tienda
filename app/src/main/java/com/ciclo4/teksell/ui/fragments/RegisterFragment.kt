package com.ciclo4.teksell.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.ciclo4.teksell.R
import com.ciclo4.teksell.model.ListaUsuarios
import com.ciclo4.teksell.model.Usuario

class RegisterFragment : Fragment() {


    //override fun onCreate(savedInstanceState: Bundle?) {
     //   super.onCreate(savedInstanceState)
    //}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newUserBtn = view.findViewById<Button>(R.id.newUserBtn)
        newUserBtn.setOnClickListener {
            if(addUser(view)){
                showAlert("Registro exitoso!", "El usuario ha sido creado exitosamente, ¡Bienvenido!")
                findNavController().navigate(R.id.initialViewFragment)
            }
        }
    }

    fun addUser(view: View):Boolean{

        val name = view.findViewById<EditText>(R.id.newNamesEt).text.toString()
        val user = view.findViewById<EditText>(R.id.newUsernameEt).text.toString()
        val email = view.findViewById<EditText>(R.id.newEmailEt).text.toString()
        val address = view.findViewById<EditText>(R.id.newDirectionEt).text.toString()
        val contact = view.findViewById<EditText>(R.id.phoneEt).text.toString()
        val pass1 = view.findViewById<EditText>(R.id.newPasswordEt).text.toString()
        val pass2 = view.findViewById<EditText>(R.id.newConfirmPasswordEt).text.toString()

        val data = arrayOf(name, user, email, address, contact, pass1, pass2)
        for (item in data){
            if(item.isEmpty()){
                showAlert("Datos incompletos", "Por favor completa todos los datos")
                return false
            }
        }
        if(pass1 != pass2){
            showAlert("Contraseña", "Las contraseñas no son compatibles")
            return false
        }
        if(!email.contains('@') || !email.endsWith(".com")){
            showAlert("Email", "El formato del correo electronico no coincide")
            return false
        }
        if(ListaUsuarios.isUser(user)){
            showAlert("Usuario", "El nombre de usuario ya existe")
            return false
        }

        ListaUsuarios.addUser(Usuario(name,user,pass1,email,address,contact))
        return true

    }

    fun showAlert(alert:String, message:String){
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }
        builder?.setTitle(alert)
        builder?.setMessage(message)
        builder?.show()
    }
}