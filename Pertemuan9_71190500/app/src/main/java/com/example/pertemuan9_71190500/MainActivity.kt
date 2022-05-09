package com.example.pertemuan9_71190500

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.widget.addTextChangedListener
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.language.bm.Languages
import java.util.*

class MainActivity: AppCompatActivity() {
    var SharedPrefed: SharedPreferences? = null
    var SharedPrefedEdit: SharedPreferences.Editor? = null
    lateinit var local: Locale
    var currentLang = " "
    var currentStrLang: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SharedPrefed = getSharedPreferences("SharePref", MODE_PRIVATE)
        SharedPrefedEdit = SharedPrefed?.edit()
        val lastClick = SharedPrefed?.getInt("LastClick", 0)
        val progress = SharedPrefed?.getInt("OnProgress", 10)

        currentLang = intent.getStringExtra(currentStrLang).toString()

        val login = findViewById<TextView>(R.id.login)
        login.setText(SharedPrefed?.getString("Login", "").toString())

        if (SharedPrefed?.getBoolean("isLogin", false) == true) {
            setContentView(R.layout.activity_menu)
            val butLogout = findViewById<AppCompatButton>(R.id.buttonLogout)
            butLogout.setOnClickListener {
                isLogout() }
            val languages = resources.getStringArray(R.array.Languages)
            val spinner = findViewById<Spinner>(R.id.spinner)
            val welcome = findViewById<TextView>(R.id.welcome)

            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, languages)
            spinner.adapter = adapter
            spinner.setSelection(lastClick!!)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    welcome.text = languages.get(p2)
                    welcome.setText(SharedPrefed?.getString("language", "").toString())
                    welcome.addTextChangedListener { it ->
                        SharedPrefedEdit?.putString("language", it.toString())
                        SharedPrefedEdit?.commit()

                    }
                    //SharedPrefedEdit?.putInt("LastClick", p2)
                    //SharedPrefedEdit?.commit()

                    when (p2) {
                        0 -> setLocale("en")
                        1 -> setLocale("id") }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    welcome.text = "Pilih Bahasa Terlebih Dahulu"
                }
            }
        }

        else{
            setContentView(R.layout.activity_main)
            val username =
                findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.username)
            val password =
                findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.password)
            val login = findViewById<AppCompatButton>(R.id.login)
            when (lastClick) {
                0 -> setLocale("english")
                1 -> setLocale("indonesia")

            }
            login.setOnClickListener {
                isLogin(username.text.toString(), password.text.toString())
            }
        }

    }
    fun setLocale(LocalName: String) {
        if (LocalName != currentLang) {
            local = Locale(LocalName)
            val res = resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.locale = local
            res.updateConfiguration(conf, dm)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(currentStrLang, LocalName)
            startActivity(intent)
            finish()

        } else {}
    }fun isLogin(userName : String, passcode : String){
        if(userName.equals("Verena") && passcode.equals("12345678")){
            SharedPrefedEdit?.putBoolean("isLogin", true)
            SharedPrefedEdit?.commit()
            val i = Intent(this, MainActivity:: class.java)
            startActivity(i)
            finish()

        }else{
            Toast.makeText(this,"Username & Password Not Valid", Toast.LENGTH_LONG).show()
        }

    }
    fun isLogout(){

        SharedPrefedEdit?.putBoolean("isLogin", false)
        SharedPrefedEdit?.commit()
        val i = Intent(this, MainActivity:: class.java)
        startActivity(i)
        finish()

    }

}
