package com.login.latihan_pertemuan6

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class SecondFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentB = inflater.inflate(R.layout.fragment_b, container, false)
        val buttonSecondFragment = fragmentB.findViewById<Button>(R.id.buttonSecondFragment)

        buttonSecondFragment.setOnClickListener {
            val mulai = Intent(context, MainActivityThird::class.java)
            startActivity(mulai)
        }
        return fragmentB


    }
}