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


        // Start of hair image appearing when spinner selects it

        val img = findViewById<ImageView>(R.id.imageView2)
        img.layoutParams = LinearLayout.LayoutParams(186, 269)

        val imgResIdHair1 = R.drawable.hair_long
        val imgResIdHair2 = R.drawable.hair_short
        val imgResIdHair3 = R.drawable.hair_fuzzy


        binding.spinnerHair.onItemSelectedListener = object :


            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //needs to figure out layering constraints
                    img.x = 540F - (img.width / 2)
                    img.y = 60F + (img.height / 2)
                when {
                    binding.spinnerHair.selectedItem.toString() == "Long" -> {
                        img.setImageResource(imgResIdHair1)
                    }
                    binding.spinnerHair.selectedItem.toString() == "Short" -> {
                        img.setImageResource(imgResIdHair2)
                    }
                    binding.spinnerHair.selectedItem.toString() == "Fuzzy" -> {
                        img.setImageResource(imgResIdHair3)
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                img.setImageResource(imgResIdHair1) // never uses this, just requires something here
            }

        }

        // End of hair image appearing when spinner selects it

        val earList = res.getStringArray(R.array.ears)
        val earArrayAdapter = ArrayAdapter(this, R.layout.custom_spinner_item, earList)
        binding.spinnerEars.adapter = earArrayAdapter

        // Start of eyes image appearing when spinner selects it

        val img1 = findViewById<ImageView>(R.id.imageView2)
        img.layoutParams = LinearLayout.LayoutParams(186, 269)

        val imgResIdEyes1 = R.drawable.eyes_round //this is the wide eyes
        val imgResIdEyes2 = R.drawable.eyes_squinted
        val imgResIdEyes3 = R.drawable.eyes_lazy


        binding.spinnerEyes.onItemSelectedListener = object :


            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //needs to figure out layering constraints
                img1.x = 540F - (img.width / 2)
                img1.y = 60F + (img.height / 2)
                when {
                    binding.spinnerEyes.selectedItem.toString() == "Wide" -> {
                        img.setImageResource(imgResIdEyes1)
                    }
                    binding.spinnerEyes.selectedItem.toString() == "Squinted" -> {
                        img.setImageResource(imgResIdEyes2)
                    }
                    binding.spinnerEyes.selectedItem.toString() == "Lazy" -> {
                        img.setImageResource(imgResIdEyes3)
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                img.setImageResource(imgResIdEyes1) // never uses this, just requires something here
            }

        }

        // End of eyes image appearing when spinner selects it

        val eyeList = res.getStringArray(R.array.eyes)
        val eyeArrayAdapter = ArrayAdapter(this, R.layout.custom_spinner_item, eyeList)
        binding.spinnerEyes.adapter = eyeArrayAdapter

        // Start of eyes image appearing when spinner selects it

        val img2 = findViewById<ImageView>(R.id.imageView2)
        img.layoutParams = LinearLayout.LayoutParams(186, 269)

        val imgResIdEars1 = R.drawable.ears_round
        val imgResIdEars2 = R.drawable.ears_pointed
        val imgResIdEars3 = R.drawable.ears_square


        binding.spinnerEars.onItemSelectedListener = object :


            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //needs to figure out layering constraints
                img2.x = 540F - (img.width / 2)
                img2.y = 60F + (img.height / 2)
                when {
                    binding.spinnerEars.selectedItem.toString() == "Round" -> {
                        img.setImageResource(imgResIdEars1)
                    }
                    binding.spinnerEars.selectedItem.toString() == "Pointed" -> {
                        img.setImageResource(imgResIdEars2)
                    }
                    binding.spinnerEars.selectedItem.toString() == "Square" -> {
                        img.setImageResource(imgResIdEars3)
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                img.setImageResource(imgResIdEars1) // never uses this, just requires something here
            }

        }

        // End of Ears image appearing when spinner selects it





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