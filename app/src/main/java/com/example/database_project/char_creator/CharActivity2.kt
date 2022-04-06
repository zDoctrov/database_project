package com.example.database_project.char_creator

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import com.example.database_project.R
import com.example.database_project.databinding.CharCreate1Binding
import com.example.database_project.databinding.CharCreate2Binding

class CharActivity2 : AppCompatActivity() {
    private lateinit var binding : CharCreate2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.char_create_2)
        val res: Resources = resources              //Needed to access strings.xml where the spinner options are stored

        //Set up binding, so you can reference the ID's of things on this activity's layout
        binding = CharCreate2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setup ArrayAdapters for the data used inside the spinners
        val hairList = res.getStringArray(R.array.hair)
        val hairArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, hairList)
        binding.spinnerHair.adapter = hairArrayAdapter

        val earList = res.getStringArray(R.array.ears)
        val earArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, earList)
        binding.spinnerEars.adapter = earArrayAdapter

        val eyeList = res.getStringArray(R.array.eyes)
        val eyeArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, eyeList)
        binding.spinnerEyes.adapter = eyeArrayAdapter

        //why are we calling finish here
        val button = findViewById<Button>(R.id.back2Btn)
        button.setOnClickListener{
            finish()
        }

        val button2 = findViewById<Button>(R.id.continue2Btn)
        button2.setOnClickListener{
            //Get user inputs from layout
            val hairChoice = binding.spinnerHair.selectedItem.toString()
            val earChoice = binding.spinnerEars.selectedItem.toString()
            val eyeChoice = binding.spinnerEyes.selectedItem.toString()

            //Save data in session object, so we can deposit all data into the DB at the same time on CharActivity3
            creationSession.hair = hairChoice
            creationSession.ears = earChoice
            creationSession.eyes = eyeChoice

            //Check what's stored
            creationSession.printAllData()

            val intent = Intent(this, CharActivity3::class.java)
            startActivity(intent)
        }//end buttonListener onClick

    }//end onCreate
}//end CharActivity2