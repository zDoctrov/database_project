package com.example.database_project.char_creator

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
//import com.demo.roomonetoonedemo.db.Address
//import com.demo.roomonetoonedemo.db.RoomAppDB
//import com.demo.roomonetoonedemo.db.UserEntity
import com.example.database_project.R
import com.example.database_project.databinding.CharCreate3Binding
import com.example.database_project.room_db.*

class CharActivity3 : AppCompatActivity() {
    private lateinit var binding : CharCreate3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.char_create_3)
        val res: Resources = resources              //Needed to access strings.xml where the spinner options are stored

        //Set up binding, so you can reference the ID's of things on this activity's layout
        binding = CharCreate3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setup ArrayAdapters for the data used inside the spinners
        val classList = res.getStringArray(R.array.user_class)
        val classArrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, classList)
        binding.spinnerClass.adapter = classArrayAdapter


    val backButton = findViewById<Button>(R.id.back3Btn)
    backButton.setOnClickListener{
        finish()
    }

    //Place character into DB, pop off all 3 'CharActivity' activities from runtime stack, and Toast the character's addition to the user
    val finishButton = findViewById<Button>(R.id.btnFinish)
        finishButton.setOnClickListener{
            //Get user inputs from layout
            val classChoice = binding.spinnerClass.selectedItem.toString()

            //Save data in session object, so we can deposit all data into the DB at the same time on CharActivity3
            creationSession.user_class = classChoice
            getStartingCurrency(classChoice)

//            val userDao = RoomAppDB.getAppDatabase(this)?.userDao()
//
//            val userEntity = UserEntity(0, "Zac", "123", "lol@email")
//            val id = userDao?.insertUser(userEntity)
//
//            val addressEntity = Address(0, id!!.toInt(), "some address 123", "some city", "some state", "some zip")
//            userDao?.insertAddress(addressEntity)

            val characterDao = RoomAppDB.getAppDatabase(this)?.characterDao()

            val subjectEntity = SubjectEntity(0, creationSession.name)
            val id = characterDao?.insertSubject(subjectEntity)

            val buildEntity = BuildEntity(0, id!!.toInt(), creationSession.race, creationSession.hair, creationSession.ears, creationSession.eyes)
            characterDao?.insertBuild(buildEntity)

            val factionEntity = FactionEntity(0, id!!.toInt(), creationSession.faction, creationSession.faction_reputation, creationSession.faction_ideology)
            characterDao?.insertFaction(factionEntity)

            val classEntity = ClassEntity(0, id!!.toInt(), creationSession.user_class, creationSession.user_currency)
            characterDao?.insertClass(classEntity)

            //Check what's stored
            creationSession.printAllData()
        }

     // We need to add functionality to Finish function
     // val button = findViewById<Button>(R.id.btnFinish)
     // button.setOnClickListener{
        //save() or send function to data base
     // }

    }

    fun getStartingCurrency(char_class: String) {
        when(char_class){
            "Noble"->{
                creationSession.user_currency = 100.0
            }
            "Brigand"->{
                creationSession.user_currency = 50.0
            }
            "Merchant"->{
                creationSession.user_currency = 20.0
            }
            "Commoner"->{
                creationSession.user_currency = 5.0
            }
            "Peasant"->{
                creationSession.user_currency = -10.0
            }
        }
    }

}