package com.example.database_project.char_creator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.database_project.R

class CharActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.char_create_2)

        val button = findViewById<Button>(R.id.back2Btn)

        button.setOnClickListener{
            finish()
        }

        val button2 = findViewById<Button>(R.id.continue2Btn)

        button2.setOnClickListener{
            val intent = Intent(this, CharActivity3::class.java)
            startActivity(intent)
        }

    }
}