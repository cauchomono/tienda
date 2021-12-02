package com.ciclo4.teksell.model

import java.io.Serializable

class Productos: Serializable {
    lateinit var marca: String
    lateinit var modelo: String
    lateinit var caracteristicas: String
    lateinit var precio: String
    lateinit var imagen : String
}

