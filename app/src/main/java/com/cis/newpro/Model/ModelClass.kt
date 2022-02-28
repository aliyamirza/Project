package com.cis.newpro.Model

class ModelClass {
    private var email:String=""
    private  var username: String=""
    private var fullname:String=""
    private var bio:String=""
    private var profileImage:String=""

    constructor(email:String, username:String, fullname:String, bio:String, profileImage:String)
    {
        this.email= email
        this.username= username
        this.fullname= fullname
        this.bio= bio
        this.profileImage= profileImage
    }

    fun getEmail():String{
        return email
    }
    fun setEmail(email: String){
        this.email=email
    }
    fun getUsername():String{
        return username
    }
    fun setUsername(username: String){
        this.username=username
    }
    fun getFullname():String{
        return fullname
    }
    fun setFullname(fullname: String){
        this.fullname=fullname
    }
    fun getBio():String{
        return bio
    }
    fun setBio(bio: String){
        this.bio=bio
    }
    fun getProfileImage():String{
        return profileImage
    }
    fun setProfileImage(profileImage: String){
        this.profileImage=profileImage
    }




}