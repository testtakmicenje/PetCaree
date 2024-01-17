package com.example.petcare.forum.model

data class Notification(

    var userid: String = "",

    var text: String = "",

    var postid: String = "",

    @field:JvmField

    var isPost: Boolean = false,

)