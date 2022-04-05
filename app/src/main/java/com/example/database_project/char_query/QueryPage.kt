package com.example.database_project.char_query

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.database_project.R

class QueryPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query_page)

        val backButton = findViewById<Button>(R.id.back4btn)

        backButton.setOnClickListener{
            finish()
        }
    }
}