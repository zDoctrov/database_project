package com.example.database_project.char_query

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.database_project.R
import com.example.database_project.char_creator.creationSession
import com.example.database_project.databinding.ActivityInstanceResultPageBinding
import com.example.database_project.databinding.CharCreate1Binding

class InstanceResultPage : AppCompatActivity() {
    private lateinit var binding : ActivityInstanceResultPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instance_result_page)

        //Set up binding, so you can reference the ID's of things on this activity's layout
        binding = ActivityInstanceResultPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get which card cell was chosen and apply their data to the activity screen as appropriately
        val position = intent.getIntExtra("position", -1)

        binding.actualNameId.text = creationSession.queryResults[position].name
        binding.actualRaceId.text = creationSession.queryResults[position].race
        binding.actualFactionId.text = creationSession.queryResults[position].faction_name
        binding.actualClassId.text = creationSession.queryResults[position].class_name
        binding.actualCreditsId.text = creationSession.queryResults[position].currency.toString()
    }
}