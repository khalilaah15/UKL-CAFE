package com.example.uklcrud

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.uklcrud.R
import com.example.uklcrud.data.MenuDatabase
import com.example.uklcrud.data.entity.Menu
import org.w3c.dom.Text

class EditorMenu : AppCompatActivity() {
    private lateinit var menu_name: EditText
    private lateinit var deskripsi: EditText
    private lateinit var harga: EditText
    private lateinit var jenis: Spinner
    private lateinit var btnSave: Button
    private lateinit var database: MenuDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editor_menu)

    init()
    setDataSpinner()
    val intent = intent.extras
    if(intent != null){
        val id = intent.getInt("id", 0)
        var menu = database.MenuDao().get(id)

        menu_name.setText(menu.menu_name)
        deskripsi.setText(menu.deskripsi)
        harga.setText(menu.harga)}

        btnSave.setOnClickListener{
            if(menu_name.text.toString().isNotEmpty() && deskripsi.text.toString().isNotEmpty() && harga.text.toString().isNotEmpty()) {
                if (intent != null) {
                    database.MenuDao().update(
                        Menu(
                            intent.getInt("id", 0),
                            menu_name.text.toString(),
                            deskripsi.text.toString(),
                            harga.text.toString(),
                            jenis.selectedItem.toString()
                        )
                    )
                } else{
                    database.MenuDao().insertAll(
                        Menu(
                            null,
                            menu_name.text.toString(),
                            deskripsi.text.toString(),
                            harga.text.toString(),
                            jenis.selectedItem.toString()
                        )
                    )
                }
                finish()
            }else {
                Toast.makeText(applicationContext, "Silahka isi semua data", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun init(){
        menu_name = findViewById(R.id.menu_name)
        deskripsi = findViewById(R.id.deskripsi)
        harga = findViewById(R.id.harga)
        jenis = findViewById(R.id.jenis)
        btnSave = findViewById(R.id.btnSave)


        database = MenuDatabase.getInstance(applicationContext)
    }

    private fun setDataSpinner(){
        val adapter = ArrayAdapter.createFromResource(applicationContext, R.array.tipe, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        jenis.adapter = adapter
    }
}


//    var tipe: String = ""
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.editor_menu)
//        menu_name = findViewById(R.id.menu_name)
//        deskripsi = findViewById(R.id.deskripsi)
//        harga = findViewById(R.id.harga)
//        btnSave = findViewById(R.id.btnSave)
//        tipe = intent.getStringExtra("jenis")!!
//
//        init()
//        setDataSpinner()
//
//        database = MenuDatabase.getInstance(applicationContext)
//
//        val intent = intent.extras
//        if (intent!=null){
//            val id = intent.getInt("id", 0)
//            val menu = database.MenuDao().get(id)
//
//            menu_name.setText(menu.menu_name)
//            deskripsi.setText(menu.deskripsi)
//            harga.setText(menu.harga)
//        }
//
//        btnSave.setOnClickListener {
//            if (menu_name.text.isNotEmpty() && deskripsi.text.isNotEmpty() && harga.text.isNotEmpty() && jenis.selectedItem.toString() != "Pilih tipe item") {
//                database.MenuDao().insertAll(
//                    Menu(
//                        null,
//                        menu_name.text.toString(),
//                        deskripsi.text.toString(),
//                        harga.text.toString(),
//                        jenis.selectedItem.toString()
//                    )
//                )
//                finish()
//            } else {
//                Toast.makeText(applicationContext,
//                    "Silahkan isi  semua data dengan valid",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//    }
