package com.example.a71190500_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth

class Start : AppCompatActivity() {
    private lateinit var login : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.awal)

        login = FirebaseAuth.getInstance()
        var user = login.currentUser

        Handler().postDelayed({
            if (user!= null){
                val masuk = Intent(this,home::class.java)
                startActivity(masuk)
                finish()
            }else{
                val log = Intent(this,MainActivity::class.java)
                startActivity(log)
                finish()
            }
        },120)
    }
}