package com.example.uklcrud

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.uklcrud.adapter.MejaAdapter
import com.example.uklcrud.data.MejaDatabase
import com.example.uklcrud.data.entity.Meja
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MejaCRUD : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf<Meja>()
    private lateinit var adapter: MejaAdapter
    private lateinit var database: MejaDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crud_meja)
        recyclerView = findViewById(R.id.recycler_View_meja)
        fab = findViewById(R.id.fab_meja)

        database = MejaDatabase.getInstance(applicationContext)
        adapter = MejaAdapter(list)
        adapter.setDialog(object : MejaAdapter.Dialog{
            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(this@MejaCRUD)
                dialog.setTitle(list[position].nomor_meja)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{ dialog, which ->
                    if (which==0){
                        val intent = Intent(this@MejaCRUD, EditorMeja::class.java)
                        intent.putExtra("id", list[position].uid)
                        startActivity(intent)
                    }else if(which==1){
                        database.MejaDao().delete(list[position])
                        getData()
                    }else {
                        dialog.dismiss()
                    }
                })
                val dialogView = dialog.create()
                dialogView.show()
            }

        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        fab.setOnClickListener {
            startActivity(Intent(this, EditorMeja::class.java))
        }
    }
    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData() {
        list.clear()
        list.addAll(database.MejaDao().getAll())
        adapter.notifyDataSetChanged()
    }
}