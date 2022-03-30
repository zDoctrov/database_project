package com.example.database_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CharActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_char2)

        val button = findViewById<Button>(R.id.back2Btn)

        button.setOnClickListener{
            val intent = Intent(this, CharActivity::class.java)
            startActivity(intent)
        }

    }
}