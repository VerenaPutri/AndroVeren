package com.example.pertemuan8_71190500

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    private lateinit var Pager : ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.toolbar)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        Pager = findViewById(R.id.viewPager)
        val listFragment : ArrayList<Fragment> = arrayListOf(FragmentKontak(),FragmentAdd(), FragmentPengaturan())
        val adapter = PagerAdapter(this, listFragment)
        Pager.adapter =adapter


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.buttonx, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.buttonContact -> Pager.currentItem = 0
            R.id.buttonAddContact -> Pager.currentItem = 1
            R.id.buttonSetting -> Pager.currentItem = 2

        }
        return true
    }
    class PagerAdapter(val fa : FragmentActivity, val listFragment : ArrayList<Fragment>): FragmentStateAdapter(fa){
        override fun getItemCount(): Int = listFragment.size
        override fun createFragment(position: Int): Fragment = listFragment[position]
    }

}