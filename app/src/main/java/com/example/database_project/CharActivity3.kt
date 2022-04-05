package com.example.database_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CharActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.char_create_3)

    val button = findViewById<Button>(R.id.back3Btn)

    button.setOnClickListener{
        finish()
    }

    val button2 = findViewById<Button>(R.id.btnFinish)

        //button2.setOnClickListener{
        //this should save the character
   // }
    }
}