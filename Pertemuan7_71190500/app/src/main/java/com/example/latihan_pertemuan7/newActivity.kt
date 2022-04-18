package com.example.latihan_pertemuan7

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class newActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val image = findViewById<ImageView>(R.id.gambarKontak)
        val nama = findViewById<TextView>(R.id.namaKontak)
        val kontak = findViewById<TextView>(R.id.nomorTelepon)
        val pesan = findViewById<TextView>(R.id.pesan)
        val wa = findViewById<TextView>(R.id.WhatsApp)
        val tele = findViewById<TextView>(R.id.Telegram)

        val bundle:Bundle?=intent.extras
        val imageId = bundle!!.getInt("imageId")
        val name = bundle.getString("teksView2")
        val nomor = bundle.getString("teksView3")

        image.setImageResource(imageId)
        nama.text=name
        kontak.text=nomor
        pesan.text=nomor
        wa.text=nomor
        tele.text=nomor


    }

}