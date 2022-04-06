package com.example.database_project.char_query

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import com.example.database_project.R
import com.example.database_project.databinding.ActivityQueryPageBinding
import com.example.database_project.databinding.CharCreate2Binding

class QueryPage : AppCompatActivity() {
    private lateinit var binding : ActivityQueryPageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query_page)
        val res: Resources = resources

        val backButton = findViewById<Button>(R.id.back4btn)

        binding= ActivityQueryPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setup ArrayAdapters for the data used inside the spinners
        val raceList = res.getStringArray(R.array.races)
        val race2ArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, raceList)
        binding.spinnerRace2.adapter = race2ArrayAdapter



        backButton.setOnClickListener{
            finish()
        }
    }
}