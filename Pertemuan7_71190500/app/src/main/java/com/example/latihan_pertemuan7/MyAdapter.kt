package com.example.latihan_pertemuan7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter (private val newInformation : ArrayList<MyInformation>):

    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemlist, parent, false)
        return MyViewHolder(itemView,mListener)

    }
    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {

        val currentItem = newInformation[position]
        holder.itemList.setImageResource(currentItem.itemList)
        holder.teksView2.text = currentItem.teksView2
        holder.teksView3.text = currentItem.teksView3
    }
    override fun getItemCount(): Int {
        return newInformation.size
    }

    private lateinit var mListener : onItemClicklistener

    interface onItemClicklistener{
        fun onItemClick(position : Int)
    }
    fun setOnItemClickListener(listener:onItemClicklistener){
        mListener = listener
    }





    class MyViewHolder(itemView: View, listener: onItemClicklistener) : RecyclerView.ViewHolder(itemView) {
        val teksView3: TextView = itemView.findViewById(R.id.teksView3)
        val itemList : ShapeableImageView = itemView.findViewById(R.id.item_List)
        val teksView2 : TextView = itemView.findViewById(R.id.teksView2)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

}