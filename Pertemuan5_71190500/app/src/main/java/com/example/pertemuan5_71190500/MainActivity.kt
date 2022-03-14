package com.example.pertemuan5_71190500

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = intent.getStringExtra("user")
        val selamat = findViewById<TextView>(R.id.selamat)
        selamat.text = "Selamat Datang ${username} "
        val outted = findViewById<Button>(R.id.outted)
        outted.setOnClickListener {
            val start = Intent(this, LoginActivity::class.java)
            startActivity(start)
        }
    }
}