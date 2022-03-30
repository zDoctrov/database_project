package com.example.database_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CharActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_char)

        val button = findViewById<Button>(R.id.backBtn)

        button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.continueBtn)

        button2.setOnClickListener{
            val intent = Intent(this, CharActivity2::class.java)
            startActivity(intent)
        }

    }
}