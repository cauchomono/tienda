package com.ciclo4.teksell.model

class ListaUsuarios {

    companion object {
        @JvmField
        //val usuarios:MutableList<Usuario> = mutableListOf()
        val usuarios:MutableList<Usuario> = inicialData()
        fun get() : List<Usuario>{
            return usuarios
        }
        fun addUser(user:Usuario){
            println("Usuario agregado")
            usuarios.add(user)
        }
        fun isUser(user:String):Boolean{
            for (usuario in usuarios){
                if(usuario.username == user)return true
            }
            return false
        }

        fun getUser(user:String):Usuario?{
            for (usuario in usuarios){
                if(usuario.username == user)return usuario
            }
            return null
        }

        fun inicialData(): MutableList<Usuario> {
            return mutableListOf(
                Usuario(
                    "Sebastian",
                    "AresAce",
                    "123",
                    "Sebastian@hotmail.com",
                    "Direccion falsa",
                    "31265465"
                ),
                Usuario(
                    "Admin",
                    "root",
                    "root",
                    "admin@hotmail.com",
                    "Direccion falsa",
                    "123456789"
                )
            )
        }
    }

}