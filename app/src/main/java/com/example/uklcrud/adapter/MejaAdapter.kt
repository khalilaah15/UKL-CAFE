package com.example.uklcrud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uklcrud.R
import com.example.uklcrud.data.entity.Meja

class MejaAdapter(var list: List<Meja>) : RecyclerView.Adapter<MejaAdapter.ViewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog{
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nomor_meja: TextView
        var kapasitas: TextView

        init {
            nomor_meja = view.findViewById(R.id.nomor_meja)
            kapasitas = view.findViewById(R.id.kapasitas)
            view.setOnClickListener {
                dialog.onClick(layoutPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_meja, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nomor_meja.text = list[position].nomor_meja
        holder.kapasitas.text = list[position].kapasitas
    }
}