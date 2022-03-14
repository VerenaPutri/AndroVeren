package com.example.pertemuan5_71190500

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val UserName =  findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.UserName)
        val Passcode = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.Passcode)
        val Logged = findViewById<Button>(R.id.Logged)
        Logged.setOnClickListener {
            login(UserName.text.toString(), Passcode.text.toString() )
        }
    }

    fun login(username: String, password: String){
        if(password.equals("123456789")) {
            val i: Intent = Intent(this, MainActivity::class.java)
            i.putExtra("user", username)
            startActivity(i)
        }
        else{
            showMessage("Password Salah!")
        }
    }

    fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    }
}