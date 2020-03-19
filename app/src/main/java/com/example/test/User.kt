package com.example.test

class User {
    lateinit var login : String
    lateinit var avatar : String
    lateinit var url : String
    constructor(login: String,avatar:String,url:String){
        this.login = login
        this.avatar =avatar
        this.url=url
    }
}