package com.ciclo4.teksell.model

import java.io.Serializable

class Usuario(): Serializable {
    companion object{
    lateinit var name: String
    lateinit var username: String
    lateinit var password: String
    lateinit var email: String
    lateinit var address: String
    lateinit var contact : String
}
}

