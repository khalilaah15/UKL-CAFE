package com.example.uklcrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var email: EditText
    //private lateinit var password: EditText
    private lateinit var loginbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = findViewById(R.id.email)
        //password = findViewById(R.id.password)
        loginbtn = findViewById(R.id.loginbtn)

        loginbtn.setOnClickListener {
            val strEmail = email.text.toString()
            val intent = Intent(this@MainActivity, ActivityLogin::class.java)
            intent.putExtra("email", strEmail)
            startActivity(intent)
        }
    }
}
