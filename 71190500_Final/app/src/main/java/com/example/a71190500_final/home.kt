package com.example.a71190500_final


import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

class home : AppCompatActivity() {
    var firestore: FirebaseFirestore? = null
    lateinit var search: SearchView
    lateinit var listfilm: ArrayList<dataFilm>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        val googleClient = GoogleSignIn.getClient(this, signInOptions)
        val acct: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
        val buttonLogOut = findViewById<ImageButton>(R.id.btn_logout)
        firestore = FirebaseFirestore.getInstance()
        buttonLogOut.setOnClickListener {
            googleClient.signOut().addOnSuccessListener{
                FirebaseAuth.getInstance().signOut()
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            }
        }
        val buttonCreate = findViewById<FloatingActionButton>(R.id.btn_tambah)
        buttonCreate.setOnClickListener {
            val i = Intent(this, tambahActivity::class.java)
            startActivity(i)
        }

        if (acct!=null){
            val personName = acct.email
            if (personName != null) {
                firestore?.collection(personName)
                    ?.get()
                    ?.addOnSuccessListener {documents ->
                        listfilm = arrayListOf<dataFilm>()
                        listfilm.clear()
                        for (document in documents){
                            listfilm.add(
                                dataFilm(
                                document["judul"].toString(),
                                document["genre"].toString(),
                                document["produser"].toString(),
                                document["pemeranUtama"].toString(),
                                document["tahunRilis"].toString(),
                                document["akun"].toString()
                            )
                            )
                        }


                        var tampilFilm = findViewById<RecyclerView>(R.id.recyclerView)
                        tampilFilm.layoutManager=LinearLayoutManager(this@home)
                        val adapter = adapter(listfilm, this@home)
                        tampilFilm.adapter=adapter
                        search = findViewById(R.id.searchBar)
                        search.clearFocus()
                        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(query: String?): Boolean {
                                return false
                            }

                            override fun onQueryTextChange(text: String): Boolean {
                                listfilm.clear()
                                for (document in documents){
                                    if (document["judul"].toString().toLowerCase().contains(text.toLowerCase())||
                                        document["genre"].toString().toLowerCase().contains(text.toLowerCase())||
                                        document["produser"].toString().toLowerCase().contains(text.toLowerCase())||
                                        document["pemeranUtama"].toString().toLowerCase().contains(text.toLowerCase())||
                                        document["tahunRilis"].toString().toLowerCase().contains(text.toLowerCase())||
                                        document["akun"].toString().toLowerCase().contains(text.toLowerCase())
                                    ){
                                        listfilm.add(
                                            dataFilm(
                                            document["judul"].toString(),
                                            document["genre"].toString(),
                                            document["produser"].toString(),
                                            document["pemeranUtama"].toString(),
                                            document["tahunRilis"].toString(),
                                            document["akun"].toString()
                                        )
                                        )
                                    }
                                }


                                var tampilfilm = findViewById<RecyclerView>(R.id.recyclerView)
                                tampilfilm.layoutManager=LinearLayoutManager(this@home)
                                val adapter = com.example.a71190500_final.adapter(listfilm, this@home)
                                tampilFilm.adapter=adapter
                                return true
                            }
                        })




                    }
            }

        }

    }
}