package com.example.a71190500_final



import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.tambah.*

import java.util.*

class updateActivity : AppCompatActivity() {

    var firestore: FirebaseFirestore? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update)


        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        val googleClient = GoogleSignIn.getClient(this, signInOptions)
        val acct: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)

        firestore = FirebaseFirestore.getInstance()

        val accept = findViewById<Button>(R.id.btn_update)


        val ambiljudul =intent.getStringExtra("judul")
        val ambilgenre=intent.getStringExtra("genre")
        val ambilproduser=intent.getStringExtra("produser")
        val ambilpemeran=intent.getStringExtra("pemeran")
        val ambiltahunrilis=intent.getStringExtra("tahunRilis")

        val judul = findViewById<TextView>(R.id.judul)
        val genre = findViewById<EditText>(R.id.genre)
        val produser = findViewById<EditText>(R.id.produser)
        val pemeran = findViewById<EditText>(R.id.pemeran)
        val tahunRilis= findViewById<EditText>(R.id.tahunRilis)

        val bundle: Bundle = intent.extras!!
        judul.text = ambiljudul
        genre.setText(ambilgenre)
        produser.setText(ambilproduser)
        pemeran.setText(ambilpemeran)
        tahunRilis.setText(ambiltahunrilis)


        accept.setOnClickListener {

            if (judul!=null && genre!=null && produser!=null && pemeran!=null && tahunRilis!=null){
                if (acct!=null){

                    val personName = acct.email
                    val albumLagu = dataFilm(judul.text.toString(), genre.text.toString(), produser.text.toString(), pemeran.text.toString(),tahunRilis.text.toString(),personName.toString())
                    if (firestore?.collection(personName.toString())
                            ?.document(judul.text.toString()) == null){
                        Toast.makeText(this, "Film sudah ada", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        firestore?.collection(personName.toString())?.document(albumLagu.judul)
                            ?.set(albumLagu)
                            ?.addOnSuccessListener {
                                val i = Intent(this, home::class.java)
                                startActivity(i)
                                finish()
                                Toast.makeText(
                                    this,
                                    "Berhasil",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }?.addOnFailureListener {
                                Toast.makeText(this, "Gagal. Coba Lagi!!", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    }
                }
            }
            else{
                Toast.makeText(this, "Data harus diisi semua", Toast.LENGTH_SHORT).show()
            }
        }
    }
}