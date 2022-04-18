package com.example.latihan_pertemuan7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var newRecylerview : RecyclerView
    private lateinit var newArrayList : ArrayList<MyInformation>
    lateinit var tempArrayList: ArrayList<MyInformation>
    lateinit var imageId : Array<Int>
    lateinit var teksView2 : Array<String>
    lateinit var teksView3: Array<String>
    lateinit var nomorTelepon:Array<String>
    lateinit var pesan:Array<String>
    lateinit var whatsapp:Array<String>
    lateinit var telegram:Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        setContentView(R.layout.activity_main)

        imageId = arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i,
            R.drawable.j

        )
        teksView2 = arrayOf(
            "Princess Anna",
            "Queen Elsa",
            "Papa Hector",
            "Mama Imelda",
            "Maui",
            "Mei Lee",
            "Mirable",
            "Red Bird",
            "Luca",
            "Moana"

        )
        teksView3 = arrayOf(
            "081234567890",
            "081098765432",
            "081987654321",
            "082345678901",
            "082123454654",
            "082345123456",
            "082323434565",
            "081232123343",
            "087678643746",
            "087645276354"
        )


        newRecylerview = findViewById(R.id.recycleView)
        newRecylerview.layoutManager = LinearLayoutManager (this)
        newRecylerview.setHasFixedSize(true)

        newArrayList = arrayListOf<MyInformation>()
        tempArrayList = arrayListOf<MyInformation>()
        getUserData()




    }

    private fun getUserData() {
        for(i in imageId.indices){
            val myInformation = MyInformation(imageId[i],teksView2[i],teksView3[i])
            newArrayList.add(myInformation)
        }
        tempArrayList.addAll(newArrayList)
        var adapter = MyAdapter(newArrayList)
        newRecylerview.adapter = adapter
        adapter.setOnItemClickListener(object : MyAdapter.onItemClicklistener {
            override fun onItemClick(position : Int){
                val i = Intent(this@MainActivity,newActivity::class.java)
                i.putExtra("imageId",newArrayList[position].itemList)
                i.putExtra("teksView2",newArrayList[position].teksView2)
                i.putExtra("teksView3",newArrayList[position].teksView3)
                startActivity(i)
            }

        })



    }
}