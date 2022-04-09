package com.example.database_project.char_query

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import com.example.database_project.R
import com.example.database_project.char_creator.CharActivity2
import com.example.database_project.char_creator.creationSession
import com.example.database_project.databinding.ActivityQueryPageBinding
import com.example.database_project.room_db.RoomAppDB

class QueryPage : AppCompatActivity() {
    private lateinit var binding : ActivityQueryPageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query_page)
        val res: Resources = resources

        binding= ActivityQueryPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setup ArrayAdapters for the data used inside the spinners
        val raceList = res.getStringArray(R.array.races_empty)
        val race2ArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, raceList)
        binding.spinnerRace2.adapter = race2ArrayAdapter

        //Setup ArrayAdapters for the data used inside the spinners
        val factionList = res.getStringArray(R.array.factions_empty)
        val faction2ArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, factionList)
        binding.spinnerFaction2.adapter = faction2ArrayAdapter


        val hairList = res.getStringArray(R.array.hair_empty)
        val hair2ArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, hairList)
        binding.spinnerHair2.adapter = hair2ArrayAdapter

        val earsList = res.getStringArray(R.array.ears_empty)
        val ears2ArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, earsList)
        binding.spinnerEars2.adapter = ears2ArrayAdapter

        val eyesList = res.getStringArray(R.array.eyes_empty)
        val eyes2ArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, eyesList)
        binding.spinnerEyes2.adapter = eyes2ArrayAdapter

        val classList = res.getStringArray(R.array.user_class_empty)
        val class2ArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, classList)
        binding.spinnerClass2.adapter = class2ArrayAdapter


        val searchButton = findViewById<Button>(R.id.searchBtn)
        searchButton.setOnClickListener{
            //Read Data from database
            val characterDao = RoomAppDB.getAppDatabase(this)?.characterDao()
            creationSession.queryResults = characterDao?.getAllFourTablesJoined()!!

            println("Number of Results: ${creationSession.queryResults.size}\n")

            creationSession.queryResults?.forEach{
                println("Record #${it.id}: Name=${it.name}, Race=${it.race}, Faction=${it.faction_name}\n")
            }


            val intent = Intent(this, QuerySearchResults::class.java)
            startActivity(intent)
        }

        val backButton = findViewById<Button>(R.id.back4btn)
        backButton.setOnClickListener{
            finish()
        }
    }
}