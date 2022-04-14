package com.example.database_project.char_query

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.example.database_project.MainActivity
import com.example.database_project.R
import com.example.database_project.char_creator.creationSession
import com.example.database_project.databinding.ActivityInstanceResultPageBinding
import com.example.database_project.databinding.CharCreate1Binding
import com.example.database_project.room_db.RoomAppDB

class InstanceResultPage() : AppCompatActivity() {
    private lateinit var binding : ActivityInstanceResultPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instance_result_page)
        val res: Resources = resources

        //Set up binding, so you can reference the ID's of things on this activity's layout
        binding = ActivityInstanceResultPageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //for layering the images
        var imageNameRace = "@drawable/"
        var imageNameHair = "@drawable/"
        var imageNameEyes = "@drawable/"
        var imageNameEars = "@drawable/"

        val positionRace = intent.getIntExtra("position", -1)
        val raceType = creationSession.queryResults[positionRace].race

        val positionHair = intent.getIntExtra("position", -1)
        val HairType = creationSession.queryResults[positionHair].hair

        val positionEyes = intent.getIntExtra("position", -1)
        val EyeType = creationSession.queryResults[positionEyes].eyes

        val positionEars = intent.getIntExtra("position", -1)
        val EarType = creationSession.queryResults[positionEars].ears

        when(raceType){
            "Human"->{
                imageNameRace = "@drawable/race_human"
            }
            "Dwarf"->{
                imageNameRace = "@drawable/race_dwarf"
            }
            "Elf"->{
                imageNameRace = "@drawable/race_elf"
            }
        }

        when(HairType){
            "Long"->{
                imageNameHair = "@drawable/hair_long"
            }
            "Short"->{
                imageNameHair = "@drawable/hair_short"
            }
            "Fuzzy"->{
                imageNameHair = "@drawable/hair_fuzzy"
            }
        }

        when(EyeType){
            "Round"->{
                imageNameEyes = "@drawable/eyes_round"
            }
            "Squinted"->{
                imageNameEyes = "@drawable/eyes_squinted"
            }
            "Lazy"->{
                imageNameEyes = "@drawable/eyes_lazy"
            }
        }

        when(EarType){
            "Round"->{
                imageNameEars = "@drawable/ears_round"
            }
            "Pointed"->{
                imageNameEars = "@drawable/ears_pointed"
            }
            "Square"->{
                imageNameEars = "@drawable/ears_square"
            }
        }

        var imageResId1= resources.getIdentifier(imageNameRace, "drawable", packageName)
        var imageResId2= resources.getIdentifier(imageNameHair, "drawable", packageName)
        var imageResId3 = resources.getIdentifier(imageNameEyes, "drawable", packageName)
        var imageResId4 = resources.getIdentifier(imageNameEars, "drawable", packageName)

        binding.imageView4.layoutParams = LinearLayout.LayoutParams(600, 900)
        binding.imageView4.x = 230F
        binding.imageView4.y = -150F
        binding.imageView11.layoutParams = LinearLayout.LayoutParams(600, 900)
        binding.imageView11.x = 230F
        binding.imageView11.y = -150F
        binding.imageView12.layoutParams = LinearLayout.LayoutParams(600, 900)
        binding.imageView12.x = 230F
        binding.imageView12.y = -150F
        binding.imageView13.layoutParams = LinearLayout.LayoutParams(600, 900)
        binding.imageView13.x = 230F
        binding.imageView13.y = -150F

        binding.imageView4.setImageResource(imageResId1)
        binding.imageView13.setImageResource(imageResId2)
        binding.imageView12.setImageResource(imageResId3)
        binding.imageView11.setImageResource(imageResId4)


        //Get which card cell was chosen and apply their data to the activity screen as appropriately
        val position = intent.getIntExtra("position", -1)

        binding.actualNameId.text = creationSession.queryResults[position].name
        binding.actualRaceId.text = creationSession.queryResults[position].race
        binding.actualFactionId.text = creationSession.queryResults[position].faction_name
        binding.actualClassId.text = creationSession.queryResults[position].class_name
        binding.actualCreditsId.text = creationSession.queryResults[position].currency.toString()

        binding.btnDeleteCharacter.setOnClickListener(){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Are you sure?")
            builder.setMessage("Deleting a character cannot be undone!")
            builder.setPositiveButton("Yes") { _: DialogInterface, i: Int ->
                val characterDao = RoomAppDB.getAppDatabase(this)?.characterDao()
                characterDao?.delete(creationSession.queryResults[position].id)

                val deleteIntent = Intent(this, QuerySearchResults::class.java)
                    .putExtra("position", position)
                setResult(RESULT_OK, deleteIntent)

                finish()
            }
            builder.setNegativeButton("No") { _: DialogInterface, i: Int ->
            }
            builder.show()
        }

        binding.btnEditCharacter.setOnClickListener(){
            val intent = Intent(this, QueryCharEditPage::class.java)
                .putExtra("position", position)

            startActivity(intent)
        }

        binding.btnHome.setOnClickListener(){
            //Sends user back to start screen
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}