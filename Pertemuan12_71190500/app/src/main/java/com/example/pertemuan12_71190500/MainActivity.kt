package com.example.pertemuan12_71190500

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val inputCity = findViewById<EditText>(R.id.inputCity)

        btnSearch.setOnClickListener {
            checkCity(inputCity.text.toString())

        }
    }
    fun checkCity(city : String){


        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openweathermap.org/data/2.5/forecast?q=${city}&appid=201e1a52bf6886016eaee9123aca1b28"

// Request a string response from the provided URL.
        val Request = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
               val ambilData = JSONObject(response)
                val cuaca = ambilData.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("description")
                val temperatur = ambilData.getJSONArray("list").getJSONObject(0).getJSONObject("main").getDouble("temp")
                val today = findViewById<TextView>(R.id.textToday)
                today.text="TODAY \n\n CUACA: ${cuaca} \n\n TEMPERATUR:${String.format("%.2f",temperatur - 273.15).toDouble()}\u2103"

                val weathertom = ambilData.getJSONArray("list").getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("description")
                val temptom = ambilData.getJSONArray("list").getJSONObject(1).getJSONObject("main").getDouble("temp")
                val tomm = findViewById<TextView>(R.id.textTommorow)
                tomm.text="TOMMOROW \n\n CUACA: ${weathertom} \n\n TEMPERATUR:${String.format("%.2f",temptom - 273.15).toDouble()}\u2103"

                val cualus = ambilData.getJSONArray("list").getJSONObject(2).getJSONArray("weather").getJSONObject(0).getString("description")
                val templus = ambilData.getJSONArray("list").getJSONObject(2).getJSONObject("main").getDouble("temp")
                val lusa = findViewById<TextView>(R.id.textLusa)
                lusa.text="LUSA \n\n CUACA: ${cualus} \n\n TEMPERATUR:${String.format("%.2f",templus - 273.15).toDouble()}\u2103"
            },
            Response.ErrorListener {Toast.makeText(this,"Masukan Kota yang benar",Toast.LENGTH_LONG).show()})

// Add the request to the RequestQueue.
        queue.add(Request)

    }
}