package com.example.database_project.char_creator

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.database_project.R
import com.example.database_project.databinding.CharCreate1Binding
import com.google.android.material.snackbar.Snackbar


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
        val raceArrayAdapter = ArrayAdapter(this, R.layout.custom_spinner_item, raceList)
        binding.spinnerRace.adapter = raceArrayAdapter

        val factionList = res.getStringArray(R.array.factions)
        val factionArrayAdapter = ArrayAdapter(this, R.layout.custom_spinner_item, factionList)
        binding.spinnerFaction.adapter = factionArrayAdapter



        val race = binding.spinnerRace.selectedItem.toString()

        // Start of race image appearing when spinner selects it

        val img = findViewById<ImageView>(R.id.imageViewRace)
        img.layoutParams = LinearLayout.LayoutParams(186, 269)

        val imgResId1 = R.drawable.race_human // blank human image
        val imgResId2 = R.drawable.race_dwarf // blank dwarf image
        val imgResId3 = R.drawable.race_elf // blank elf image


        binding.spinnerRace.onItemSelectedListener = object :


            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                img.x = 540F - (img.width / 2)
                img.y = 600F + (img.height / 2)
                when {
                    binding.spinnerRace.selectedItem.toString() == "Human" -> {
                        img.setImageResource(imgResId1)
                    }
                    binding.spinnerRace.selectedItem.toString() == "Dwarf" -> {
                        img.setImageResource(imgResId2)
                    }
                    binding.spinnerRace.selectedItem.toString() == "Elf" -> {
                        img.setImageResource(imgResId3)
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                img.setImageResource(imgResId1) // never uses this, just requires something here
            }

        }

        // End of race image appearing when spinner selects it

        // Start of faction image appearing when spinner selects it

        val img2 = findViewById<ImageView>(R.id.imageViewFaction)
        img2.layoutParams = LinearLayout.LayoutParams(240, 300)

        val imgResId4 = R.drawable.smoke // merchants guild faction image
        val imgResId5 = R.drawable.tank // free guard faction image
        val imgResId6 = R.drawable.temple // iron company faction image
        val imgResId7 = R.drawable.tractor // united scholars faction image
        val imgResId8 = R.drawable.academic // dastardly deacons faction image


        binding.spinnerFaction.onItemSelectedListener = object :


            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                img2.x = 540F - (img2.width / 2)
                img2.y = 1140F + (img2.height / 2)
                when {
                    binding.spinnerFaction.selectedItem.toString() == "Merchants Guild" -> {
                        img2.setImageResource(imgResId4)
                    }
                    binding.spinnerFaction.selectedItem.toString() == "Free Guard" -> {
                        img2.setImageResource(imgResId5)
                    }
                    binding.spinnerFaction.selectedItem.toString() == "Iron Company" -> {
                        img2.setImageResource(imgResId6)
                    }
                    binding.spinnerFaction.selectedItem.toString() == "United Scholars" -> {
                        img2.setImageResource(imgResId7)
                    }
                    binding.spinnerFaction.selectedItem.toString() == "Dastardly Deacons" -> {
                        img2.setImageResource(imgResId8)
                    }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                img2.setImageResource(imgResId4) // never uses this, just requires something here
            }

        }

        // End of faction image appearing when spinner selects it


        val backButton = findViewById<Button>(R.id.backBtn)
        backButton.setOnClickListener{
            finish()
        }

        val continueButton = findViewById<Button>(R.id.continueBtn)
        continueButton.setOnClickListener{ view ->
            //Get user inputs from layout
            var nameChoice: String = binding.userInputName.text.toString()
            val raceChoice = binding.spinnerRace.selectedItem.toString()
            val factionChoice = binding.spinnerFaction.selectedItem.toString()


            //Save data in session object, so we can deposit all data into the DB at the same time on CharActivity3
            creationSession.name = nameChoice
            creationSession.race = raceChoice
            creationSession.faction = factionChoice
            getFactionAttributes(factionChoice)

            //Check what's stored
            creationSession.printAllData()

            //Throw error if the user doesn't input any data for name
            if(creationSession.name.trim() == "")
            {
                Snackbar.make(view, "Enter a name for your creation!", Snackbar.LENGTH_LONG).show()
            }
            else{
                val intent = Intent(this, CharActivity2::class.java)
                startActivity(intent)



                intent.putExtra("Race", race)
                startActivity(intent)
            }
        }

        //binding.userInputName ????

    }//end on Create


    fun getFactionAttributes(faction: String) {
        when(faction){
            "Merchants Guild"->{
                creationSession.faction_reputation = "Positive"
                creationSession.faction_ideology = "Industrialist"
            }
            "Free Guard"->{
                creationSession.faction_reputation = "Positive"
                creationSession.faction_ideology = "Royalists"
            }
            "Iron Company"->{
                creationSession.faction_reputation = "Negative"
                creationSession.faction_ideology = "Militaristic"
            }
            "United Scholars"->{
                creationSession.faction_reputation = "Mixed"
                creationSession.faction_ideology = "Academic"
            }
            "Dastardly Deacons"->{
                creationSession.faction_reputation = "Negative"
                creationSession.faction_ideology = "Religious"
            }
        }
    }

}//end CharActivity