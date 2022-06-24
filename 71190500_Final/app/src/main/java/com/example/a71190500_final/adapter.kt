package com.example.a71190500_final

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

class adapter (var listFilm: ArrayList<dataFilm>, var context: Context): RecyclerView.Adapter<adapter.holder>() {


    val mainList = listFilm


    class holder(val view : View): RecyclerView.ViewHolder(view){

        @SuppressLint("RestrictedApi")
        fun bind(film: dataFilm, context: Context){
            val firestore = FirebaseFirestore.getInstance()
            view.findViewById<TextView>(R.id.judul).setText(film.judul)
            view.findViewById<TextView>(R.id.genre).setText(film.genre)
            view.findViewById<TextView>(R.id.produser).setText(film.produser)
            view.findViewById<TextView>(R.id.pemeran).setText(film.pemeranUtama)
            view.findViewById<TextView>(R.id.tahunRilis).setText(film.tahunRilis)
            view.setOnClickListener{
                val i: Intent = Intent(view.context, dataFilm::class.java)
                i.putExtra("judul",film.judul)
                i.putExtra("genre",film.genre)
                i.putExtra("produser",film.produser)
                i.putExtra("pemeranUtama",film.pemeranUtama)
                i.putExtra("tahunRilis",film.tahunRilis)
                context.startActivity(i)
            }
            val buttonUpdate = view.findViewById<ImageButton>(R.id.btn_update)
            buttonUpdate.setOnClickListener {
                val i: Intent = Intent(view.context, updateActivity::class.java)

                i.putExtra("judul",film.judul)
                i.putExtra("genre",film.genre)
                i.putExtra("produser",film.produser)
                i.putExtra("pemeranUtama",film.pemeranUtama)
                i.putExtra("tahunRilis",film.tahunRilis)
                context.startActivity(i)
            }
            val buttonHapus = view.findViewById<ImageButton>(R.id.btn_delete)
            buttonHapus.setOnClickListener {
                firestore?.collection(film.akun)
                    ?.document(film.judul)?.delete()
                    ?.addOnSuccessListener {
                        val i: Intent = Intent(view.context, home::class.java)
                        context.startActivity(i)
                        getActivity(view.context)?.finish()
                    }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.data, parent, false)
        return holder(v)
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        holder.bind(listFilm[position],context)
    }

    override fun getItemCount(): Int {
        return mainList.size
    }
}