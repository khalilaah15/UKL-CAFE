package com.example.uklcrud

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uklcrud.data.UserDatabase
import com.example.uklcrud.data.entity.User
import com.google.android.material.textfield.TextInputEditText

class EditorUser : AppCompatActivity() {
    private lateinit var fullName: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var btnSave: Button
    private lateinit var database: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editor_user)
        fullName = findViewById(R.id.full_name)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        btnSave = findViewById(R.id.btnSave)

        database = UserDatabase.getInstance(applicationContext)

        val intent = intent.extras
        if (intent != null) {
            val id = intent.getInt("id", 0)
            var user = database.userDao().get(id)

            fullName.setText(user.fullName)
            username.setText(user.username)
            password.setText(user.password)
        }

        btnSave.setOnClickListener {
            if (fullName.text.isNotEmpty() && username.text.isNotEmpty() && password.text.isNotEmpty()) {
                if (intent != null) {
                    database.userDao().update(
                        User(
                            intent.getInt("id", 0),
                            fullName.text.toString(),
                            username.text.toString(),
                            password.text.toString()
                        )
                    )
                } else {
                    database.userDao().insertAll(
                        User(
                            null,
                            fullName.text.toString(),
                            username.text.toString(),
                            password.text.toString()
                        )
                    )
                }
                finish()
            } else {
                Toast.makeText(applicationContext, "Silahkan isi seuai data", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}