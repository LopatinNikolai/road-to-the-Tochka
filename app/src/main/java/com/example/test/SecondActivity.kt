package com.example.test

import android.content.Intent
import android.content.Intent.getIntentOld
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.URL

class SecondActivity : AppCompatActivity() {
    private lateinit var login: TextView
    private lateinit var name: TextView
    private lateinit var publicRepos: TextView
    private lateinit var followers: TextView
    private lateinit var following: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        login = findViewById(R.id.login)
        name = findViewById(R.id.name)
        publicRepos = findViewById(R.id.public_repos)
        followers = findViewById(R.id.followers)
        following = findViewById(R.id.following)
        var intentSec: Intent = getIntent()
        if(intentSec.hasExtra("login")!== null){
            login.setText("login:"+intentSec.getStringExtra("login"))
        }
        if(intentSec.hasExtra("name")!== null){
            name.setText("name:"+intentSec.getStringExtra("name"))
        }
        if(intentSec.hasExtra("public_repos")!== null){
            publicRepos.setText("public repos:"+intentSec.getStringExtra("public_repos"))
        }
        if(intentSec.hasExtra("followers")!== null){
            followers.setText("followers:"+intentSec.getStringExtra("followers"))
        }
        if(intentSec.hasExtra("following")!== null){
            following.setText("following:"+intentSec.getStringExtra("following"))
        }
    }


}
