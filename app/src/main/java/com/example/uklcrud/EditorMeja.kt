package com.example.uklcrud

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uklcrud.data.MejaDatabase
import com.example.uklcrud.data.entity.Meja

class EditorMeja : AppCompatActivity() {
    private lateinit var nomor_meja: EditText
    private lateinit var kapasitas: EditText
    private lateinit var btnSave: Button
    private lateinit var database: MejaDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editor_meja)
        nomor_meja = findViewById(R.id.nomor_meja)
        kapasitas = findViewById(R.id.kapasitas)
        btnSave = findViewById(R.id.btnSave)

        database = MejaDatabase.getInstance(applicationContext)

        val intent = intent.extras
        if (intent!=null){
            val id = intent.getInt("id", 0)
            var meja = database.MejaDao().get(id)

            nomor_meja.setText(meja.nomor_meja)
            kapasitas.setText(meja.kapasitas)
        }

        btnSave.setOnClickListener {
            if (nomor_meja.text.isNotEmpty() && kapasitas.text.isNotEmpty()) {
                if(intent != null) {
                    database.MejaDao().update(
                        Meja(
                            intent.getInt("id", 0),
                            nomor_meja.text.toString(),
                            kapasitas.text.toString()
                        )
                    )
                }else {
                    database.MejaDao().insertAll(
                        Meja(
                            null,
                            nomor_meja.text.toString(),
                            kapasitas.text.toString()
                        )
                    )
                }
                finish()
            } else {
                Toast.makeText(applicationContext,
                    "Silahkan isi  semua data dengan valid",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}