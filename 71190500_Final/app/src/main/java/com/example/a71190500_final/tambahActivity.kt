package com.example.a71190500_final


import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.data.*
import java.util.*

class tambahActivity: AppCompatActivity() {
    var firestore: FirebaseFirestore? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambah)

        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        val googleClient = GoogleSignIn.getClient(this, signInOptions)
        val acct: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)

        firestore = FirebaseFirestore.getInstance()

        val judul = findViewById<EditText>(R.id.judul)
        val genre = findViewById<EditText>(R.id.genre)
        val produser = findViewById<EditText>(R.id.produser)
        val pemeran = findViewById<EditText>(R.id.pemeran)
        val tahun = findViewById<EditText>(R.id.tahunRilis)


        val accept = findViewById<Button>(R.id.btn_save)

        accept.setOnClickListener {
            if (judul!=null && genre!=null && produser!=null && pemeran!=null && tahunRilis!=null){
                if (acct!=null){

                    val personName = acct.email
                    val albumLagu = dataFilm(judul.text.toString(), genre.text.toString(), produser.text.toString(), pemeran.text.toString(), tahun.text.toString(), personName.toString())
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
                                    "Proses Penyimpanan Berhasil",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }?.addOnFailureListener {
                                Toast.makeText(this, "Proses Penyimpanan Gagal", Toast.LENGTH_SHORT)
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