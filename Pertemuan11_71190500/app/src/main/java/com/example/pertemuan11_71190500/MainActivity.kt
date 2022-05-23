package com.example.pertemuan11_71190500

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var nama: EditText
    private lateinit var ipk : EditText
    private lateinit var nim : EditText
    private lateinit var btnsimpan : Button
    private lateinit var btntampil : Button
    private lateinit var btnsearch : Button


    val database = FirebaseDatabase.getInstance()
    val ref = database.getReference("users")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nama = findViewById(R.id.nama)
        nim = findViewById(R.id.nim)
        ipk =findViewById(R.id.ipk)
        btntampil = findViewById(R.id.btntampil)
        btnsimpan = findViewById(R.id.btnsimpan)
        btnsearch = findViewById(R.id.btnsearch)
        simpanData()
        tampilData()




    }
    //Digunakan untuk menampilkan jika terjadi kesalahan input data

    fun showToast(text:String){
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_LONG).show()
    }
    fun showDil(title: String, Message: String){
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }
    fun cleanEditText(){
        nama.setText("")
        nim.setText("")
        ipk.setText("")
    }

    fun simpanData(){
        btnsimpan.setOnClickListener {
            val dial : ProgressDialog = ProgressDialog(this)
            dial.setMessage("On Process")
            dial.show()

            val user : User = User(
                nama.text.toString(),
                nim.text.toString(),
                ipk.text.toString()
            )
            ref.child(user.nim).setValue(user).addOnCompleteListener{
                dial.dismiss()
                showToast("Data Tersimpan")
                cleanEditText()
            }
        }
    }



    fun tampilData(){
        btntampil.setOnClickListener (
            View.OnClickListener  {
                val buffer = StringBuffer()
                ref.addListenerForSingleValueEvent(object: ValueEventListener{
                    override fun onCancelled(error: DatabaseError) {

                    }
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val children = snapshot.children
                        children.forEach {
                            val user: User = it.getValue(User::class.java)!!
                            buffer.append(
                                "NAMA : " + user.nama + "\n"
                            )
                            buffer.append(
                                "NIM : " + user.nim + "\n"
                            )
                            buffer.append(
                                "IPK : " + user.ipk + "\n\n"
                            )

                        }
                        showDil("Data Mahasiswa", buffer.toString())
                    }

                })
            }
        )
    }
}