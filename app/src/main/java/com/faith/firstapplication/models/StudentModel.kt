package com.faith.firstapplication.models

data class Student(
    var id:String="",
    val name:String ="",
    val age:String ="",
    val course:String="",
    var userId: String = "",
    val imageUrl: String = ""   //  Add for storing uploaded image URL
)