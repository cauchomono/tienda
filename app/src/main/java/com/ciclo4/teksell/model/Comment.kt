package com.ciclo4.teksell.model
/*
data class Comment(

    val username:String,
    val name:String,
    val comment: String,
    val date:String,
    val score:Int,

)
*/

import java.io.Serializable

class Comment() :Serializable{

    lateinit var username:String
    lateinit var name:String
    lateinit var comment: String
    lateinit var date:String
    var score:Int = 5

}