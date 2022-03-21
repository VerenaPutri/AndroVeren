package com.login.latihan_pertemuan6

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FirstFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentA = inflater.inflate(R.layout.fragment_a, container, false)
        val buttonFirstFragment = fragmentA.findViewById<Button>(R.id.buttonFirstFragment)

        buttonFirstFragment.setOnClickListener {
            val mulai = Intent(context, MainActivityTwo::class.java)
            startActivity(mulai)
        }
        return fragmentA


    }
}