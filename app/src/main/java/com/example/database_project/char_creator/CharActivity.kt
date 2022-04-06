package com.example.database_project.char_creator

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import com.example.database_project.R
import com.example.database_project.databinding.CharCreate1Binding

class CharActivity : AppCompatActivity() {
    private lateinit var binding : CharCreate1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.char_create_1)
        val res: Resources = resources              //Needed to access strings.xml where the spinner options are stored

        //Set up binding, so you can reference the ID's of things on this activity's layout
        binding = CharCreate1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setup ArrayAdapters for the data used inside the spinners
        val raceList = res.getStringArray(R.array.races)
        val raceArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, raceList)
        binding.spinnerRace.adapter = raceArrayAdapter

        val factionList = res.getStringArray(R.array.factions)
        val factionArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, factionList)
        binding.spinnerFaction.adapter = factionArrayAdapter

        val backButton = findViewById<Button>(R.id.backBtn)
        backButton.setOnClickListener{
            finish()
        }

        val continueButton = findViewById<Button>(R.id.continueBtn)
        continueButton.setOnClickListener{
            //Get user inputs from layout
            var nameChoice: String = binding.userInputName.text.toString()
            val raceChoice = binding.spinnerRace.selectedItem.toString()
            val factionChoice = binding.spinnerFaction.selectedItem.toString()

            //TODO: Throw error if user doesn't type in a name and leaves nameChoice blank

            //Save data in session object, so we can deposit all data into the DB at the same time on CharActivity3
            creationSession.name = nameChoice
            creationSession.race = raceChoice
            creationSession.faction = factionChoice

            //Check what's stored
            creationSession.printAllData()

            val intent = Intent(this, CharActivity2::class.java)
            startActivity(intent)
        }

//        binding.userInputName

    }
}