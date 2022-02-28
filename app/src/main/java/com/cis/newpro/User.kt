package com.cis.newpro

data class Users(
    var email: String ="",
    var username: String = "",
    var password: String = "",
    var fullname: String= "",
    var bio: String= "",
    var phoneNumber:String= "",
    var profilePicture:String="",
    val userID : String =""
)
