package com.example.a71190500_final

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class detailFilm : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailfilm)

        val judul = findViewById<TextView>(R.id.judul)
        val penyanyi = findViewById<TextView>(R.id.genre)
        val album = findViewById<TextView>(R.id.produser)
        val genre = findViewById<TextView>(R.id.pemeran)
        val tanggal = findViewById<TextView>(R.id.tahunRilis)


        val ambiljudul =intent.getStringExtra("judul")
        val ambilpenyanyi=intent.getStringExtra("genre")
        val ambilalbum=intent.getStringExtra("produser")
        val ambilgenre=intent.getStringExtra("pemeran")
        val ambiltanggal=intent.getStringExtra("tahunRilis")


        judul.text=ambiljudul
        penyanyi.text=ambilpenyanyi
        album.text=ambilalbum
        genre.text=ambilgenre
        tanggal.text=ambiltanggal

        val kembali = findViewById<Button>(R.id.buttonBack)
        kembali.setOnClickListener {
            val i = Intent(this, home::class.java)
            startActivity(i)
            finish()
        }
    }
}