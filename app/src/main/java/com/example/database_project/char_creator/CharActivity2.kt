package com.example.database_project.char_creator

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.database_project.R
import com.example.database_project.databinding.CharCreate1Binding
import com.example.database_project.databinding.CharCreate2Binding
import android.util.Log

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
        val hairArrayAdapter = ArrayAdapter(this, R.layout.custom_spinner_item, hairList)
        binding.spinnerHair.adapter = hairArrayAdapter

        val earList = res.getStringArray(R.array.ears)
        val earArrayAdapter = ArrayAdapter(this, R.layout.custom_spinner_item, earList)
        binding.spinnerEars.adapter = earArrayAdapter

        val eyeList = res.getStringArray(R.array.eyes)
        val eyeArrayAdapter = ArrayAdapter(this, R.layout.custom_spinner_item, eyeList)
        binding.spinnerEyes.adapter = eyeArrayAdapter


        // Start of overlaying images as the user chooses spinners

            // get intent data
        val intent = intent
        val race = intent.getStringExtra("Race")
        val raceChoice = race.toString()


        val img = findViewById<ImageView>(R.id.imageView2)
        img.layoutParams = LinearLayout.LayoutParams(600, 900)

        val imgResId1 = R.drawable.race_human // blank human image
        val imgResId2 = R.drawable.race_dwarf // blank dwarf image
        val imgResId3 = R.drawable.race_elf // blank elf image

        img.x = 230F - (img.width / 2)

        when (raceChoice) {
            "Human" -> {
                img.setImageResource(imgResId1)
            }
            "Dwarf" -> {
                img.setImageResource(imgResId2)
            }
            "Elf" -> {
                img.setImageResource(imgResId3)
            }
        }

        // Start of eyes image appearing when spinner selects it

        val img3 = findViewById<ImageView>(R.id.imageView5)
        img3.layoutParams = LinearLayout.LayoutParams(600, 900)
        img3.x = 230F - (img3.width / 2)

        val imgResId7 = R.drawable.eyes_round // long hair image
        val imgResId8 = R.drawable.eyes_squinted // short hair image
        val imgResId9 = R.drawable.eyes_lazy // fuzzy hair image


        binding.spinnerEyes.onItemSelectedListener = object :


            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {


                when {
                    binding.spinnerEyes.selectedItem.toString() == "Round" -> {
                        img3.setImageResource(imgResId7)
                    }
                    binding.spinnerEyes.selectedItem.toString() == "Squinted" -> {
                        img3.setImageResource(imgResId8)
                    }
                    binding.spinnerEyes.selectedItem.toString() == "Lazy" -> {
                        img3.setImageResource(imgResId9)
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                img.setImageResource(imgResId7) // never uses this, just requires something here
            }

        }

        // End of eyes image appearing when spinner selects it

        // Start of ears image appearing when spinner selects it

        val img4 = findViewById<ImageView>(R.id.imageView6)
        img4.layoutParams = LinearLayout.LayoutParams(600, 900)
        img4.x = 230F - (img4.width / 2)

        val imgResId10 = R.drawable.ears_round // round ears image
        val imgResId11 = R.drawable.ears_pointed // pointed ears image
        val imgResId12 = R.drawable.ears_square // square ears image


        binding.spinnerEars.onItemSelectedListener = object :


            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {


                when {
                    binding.spinnerEars.selectedItem.toString() == "Round" -> {
                        img4.setImageResource(imgResId10)
                    }
                    binding.spinnerEars.selectedItem.toString() == "Pointed" -> {
                        img4.setImageResource(imgResId11)
                    }
                    binding.spinnerEars.selectedItem.toString() == "Square" -> {
                        img4.setImageResource(imgResId12)
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                img.setImageResource(imgResId10) // never uses this, just requires something here
            }

        }

        // End of ears image appearing when spinner selects it

        // Start of hair image appearing when spinner selects it

        val img2 = findViewById<ImageView>(R.id.imageView7)
        img2.layoutParams = LinearLayout.LayoutParams(600, 900)
        img2.x = 230F - (img2.width / 2)

        val imgResId4 = R.drawable.hair_long // long hair image
        val imgResId5 = R.drawable.hair_short // short hair image
        val imgResId6 = R.drawable.hair_fuzzy // fuzzy hair image

        binding.spinnerHair.onItemSelectedListener = object :



            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {


                when {
                    binding.spinnerHair.selectedItem.toString() == "Long" -> {
                        img2.setImageResource(imgResId4)
                    }
                    binding.spinnerHair.selectedItem.toString() == "Short" -> {
                        img2.setImageResource(imgResId5)
                    }
                    binding.spinnerHair.selectedItem.toString() == "Fuzzy" -> {
                        img2.setImageResource(imgResId6)
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                img.setImageResource(imgResId4) // never uses this, just requires something here
            }

        }

        // End of hair image appearing when spinner selects it


        // End of overlaying images as the user chooses spinners


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