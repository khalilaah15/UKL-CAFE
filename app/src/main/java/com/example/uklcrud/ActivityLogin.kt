package com.example.uklcrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.uklcrud.R
import com.example.uklcrud.UserCRUD

class ActivityLogin : AppCompatActivity() {

    private lateinit var userbtn: Button
    private lateinit var menubtn: Button
    private lateinit var mejabtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userbtn = findViewById(R.id.btn_user)
        menubtn = findViewById(R.id.btn_menu)
        mejabtn = findViewById(R.id.btn_meja)

        userbtn.setOnClickListener {
            val intent = Intent(this@ActivityLogin, UserCRUD::class.java)
            startActivity(intent)
        }

        menubtn.setOnClickListener {
            val intent = Intent(this@ActivityLogin, MenuCRUD::class.java)
            startActivity(intent)
        }

        mejabtn.setOnClickListener {
            val intent = Intent(this@ActivityLogin, MejaCRUD::class.java)
            startActivity(intent)
        }
    }
}
