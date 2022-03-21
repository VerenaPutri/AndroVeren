package com.login.latihan_pertemuan6

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ThirdFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentC = inflater.inflate(R.layout.fragment_c, container, false)
        val buttonThirdFragment = fragmentC.findViewById<Button>(R.id.buttonThirdFragment)

        buttonThirdFragment.setOnClickListener {
            val mulai = Intent(context, MainActivity::class.java)
            startActivity(mulai)
        }
        return fragmentC


    }
}