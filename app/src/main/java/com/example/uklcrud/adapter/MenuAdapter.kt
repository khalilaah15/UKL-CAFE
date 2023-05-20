package com.example.uklcrud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uklcrud.R
import com.example.uklcrud.data.entity.Menu

class MenuAdapter(var list: List<Menu>) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog{
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var menu_name: TextView
        var deskripsi: TextView
        var harga: TextView

        init {
            menu_name = view.findViewById(R.id.menu_name)
            deskripsi = view.findViewById(R.id.deskripsi)
            harga = view.findViewById(R.id.harga)
            view.setOnClickListener {
                dialog.onClick(layoutPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_menu, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.menu_name.text = list[position].menu_name
        holder.deskripsi.text = list[position].deskripsi
        holder.harga.text = list[position].harga
    }
}