package com.example.uklcrud

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.uklcrud.adapter.MenuAdapter
import com.example.uklcrud.data.MenuDatabase
import com.example.uklcrud.data.entity.Menu
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MenuCRUD : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView

    private lateinit var fab : FloatingActionButton
    private var list = mutableListOf<Menu>()
    private var list2 = mutableListOf<Menu>()


    private lateinit var database : MenuDatabase
    private lateinit var adapter : MenuAdapter
    private lateinit var adapter2 : MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crud_menu)

        recyclerView = findViewById(R.id.recycler_View_menu)
        recyclerView2 = findViewById(R.id.recycler_View_minuman)
        fab = findViewById(R.id.fab_menu)

        database = MenuDatabase.getInstance(applicationContext)
        adapter = MenuAdapter(list)
        adapter2 = MenuAdapter(list2)
        adapter.setDialog(object : MenuAdapter.Dialog{
            override fun onClick(position: Int) {
                //membuat dialog view 2
                val dialog2 = AlertDialog.Builder(this@MenuCRUD)
                dialog2.setTitle(list[position].menu_name)
                dialog2.setItems(R.array.items_option, DialogInterface.OnClickListener{dialog,which ->
                    if(which == 0){
                        //coding ubah
                        val intent2 = Intent(this@MenuCRUD, EditorMenu::class.java)
                        intent2.putExtra("id", list[position].uid)
                        startActivity(intent2)
                        //coding hapus
                    }else if (which == 1){
                        database.MenuDao().delete(list[position])
                        getData()
                        //coding batal
                    }else{
                        dialog.dismiss()
                    }
                })
                //menampilkan dilaog
                val dialogView2 = dialog2.create()
                dialogView2.show()
            }
        })

        adapter2.setDialog(object : MenuAdapter.Dialog{
            override fun onClick(position: Int) {
                //membuat dialog view 2
                val dialog2 = AlertDialog.Builder(this@MenuCRUD)
                dialog2.setTitle(list2[position].menu_name)
                dialog2.setItems(R.array.items_option, DialogInterface.OnClickListener{dialog,which ->
                    if(which == 0){
                        //coding ubah
                        val intent2 = Intent(this@MenuCRUD, EditorMenu::class.java)
                        intent2.putExtra("id", list2[position].uid)
                        startActivity(intent2)
                        //coding hapus
                    }else if (which == 1){
                        database.MenuDao().delete(list2[position])
                        getData()
                        //coding batal
                    }else{
                        dialog.dismiss()
                    }
                })
                //menampilkan dilaog
                val dialogView2 = dialog2.create()
                dialogView2.show()
            }
        })

//


        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        recyclerView2.adapter = adapter2
        recyclerView2.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView2.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        fab.setOnClickListener{
            startActivity(Intent(this,EditorMenu::class.java))


        }


    }

    override fun onResume() {
        super.onResume()
        getData()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun getData(){
        list.clear()
        list2.clear()
        list.addAll(database.MenuDao().getMenuFilterJenis("Makanan"))
        list2.addAll(database.MenuDao().getMenuFilterJenis("Minuman"))
        adapter.notifyDataSetChanged()
        adapter2.notifyDataSetChanged()
    }
}

//package com.example.uklcrud
//
//import android.annotation.SuppressLint
//import android.content.DialogInterface
//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.DividerItemDecoration
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import androidx.recyclerview.widget.RecyclerView.VERTICAL
//import com.example.uklcrud.adapter.MenuAdapter
//import com.example.uklcrud.data.MenuDatabase
//import com.example.uklcrud.data.entity.Menu
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//
//class MenuCRUD : AppCompatActivity() {
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var recyclerView2: RecyclerView
//    private lateinit var fab: FloatingActionButton
//    private var list = mutableListOf<Menu>()
//    private var list2 = mutableListOf<Menu>()
//    private lateinit var adapter: MenuAdapter
//    private lateinit var adapter2: MenuAdapter
//    private lateinit var database: MenuDatabase
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.crud_menu)
//        recyclerView = findViewById(R.id.recycler_View_menu)
//        recyclerView2 = findViewById(R.id.recycler_View_menu)
//        fab = findViewById(R.id.fab_menu)
//
//        database = MenuDatabase.getInstance(applicationContext)
//        adapter = MenuAdapter(list)
//        adapter2 = MenuAdapter(list2)
//        adapter.setDialog(object : MenuAdapter.Dialog{
//            override fun onClick(position: Int) {
//                val dialog = AlertDialog.Builder(this@MenuCRUD)
//                dialog.setTitle(list[position].menu_name)
//                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{ dialog, which ->
//                    if (which==0){
//                        val intent = Intent(this@MenuCRUD, EditorMenu::class.java)
//                        intent.putExtra("id", list[position].uid)
//                        startActivity(intent)
//                    }else if(which==1){
//                        database.MenuDao().delete(list[position])
//                        getData()
//                    }else {
//                        dialog.dismiss()
//                    }
//                })
//                val dialogView = dialog.create()
//                dialogView.show()
//            }
//
//        })
//
//        adapter2.setDialog(object : MenuAdapter.Dialog {
//            override fun onClick(position: Int) {
//                //membuat dialog view 2
//                val dialog = AlertDialog.Builder(this@MenuCRUD)
//                dialog.setTitle(list2[position].menu_name)
//                dialog.setItems(R.array.items_option,
//                    DialogInterface.OnClickListener { dialog, which ->
//                        if (which == 0) {
//                            //coding ubah
//                            val intent = Intent(this@MenuCRUD, EditorMenu::class.java)
//                            intent.putExtra("id", list2[position].uid)
//                            startActivity(intent)
//                            //coding hapus
//                        } else if (which == 1) {
//                            database.MenuDao().delete(list2[position])
//                            getData()
//                            //coding batal
//                        } else {
//                            dialog.dismiss()
//                        }
//                    })
//                //menampilkan dilaog
//                val dialogView = dialog.create()
//                dialogView.show()
//            }
//
//            private fun getData() {
//                list.clear()
//                list.addAll(database.MenuDao().getMenuFilterJenis("Makanan"))
//                adapter.notifyDataSetChanged()
//            }
//        }
//    }
//    private fun getData() {
//        list2.clear()
//        list2.addAll(database.MenuDao().getMenuFilterJenis("Minuman"))
//        adapter2.notifyDataSetChanged()
//    }
//}