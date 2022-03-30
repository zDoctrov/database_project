package com.example.database_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CharActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.char_create_2)

        val button = findViewById<Button>(R.id.back2Btn)

        button.setOnClickListener{
            val intent = Intent(this, CharActivity::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.continue2Btn)

        button2.setOnClickListener{
            val intent = Intent(this, CharActivity3::class.java)
            startActivity(intent)
        }

    }
}