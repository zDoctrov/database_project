package com.example.database_project.char_query



import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Update
import com.example.database_project.MainActivity
import com.example.database_project.R
import com.example.database_project.char_creator.creationSession
import com.example.database_project.databinding.ActivityInstanceResultPageBinding
import com.example.database_project.databinding.QueryCharEditPageBinding
import com.example.database_project.room_db.RoomAppDB

class QueryCharEditPage: AppCompatActivity() {

    private lateinit var binding: QueryCharEditPageBinding
    //Set up binding, so you can reference the ID's of things on this activity's layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.query_char_edit_page)

        binding = QueryCharEditPageBinding.inflate(layoutInflater)
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

        binding.imageView5.layoutParams = LinearLayout.LayoutParams(600, 900)
        binding.imageView5.x = 230F
        binding.imageView5.y = -150F
        binding.imageView14.layoutParams = LinearLayout.LayoutParams(600, 900)
        binding.imageView14.x = 230F
        binding.imageView14.y = -150F
        binding.imageView15.layoutParams = LinearLayout.LayoutParams(600, 900)
        binding.imageView15.x = 230F
        binding.imageView15.y = -150F
        binding.imageView16.layoutParams = LinearLayout.LayoutParams(600, 900)
        binding.imageView16.x = 230F
        binding.imageView16.y = -150F

        binding.imageView5.setImageResource(imageResId1)
        binding.imageView16.setImageResource(imageResId2)
        binding.imageView15.setImageResource(imageResId3)
        binding.imageView14.setImageResource(imageResId4)


        //Get which card cell was chosen and apply their data to the activity screen as appropriately
        val position = intent.getIntExtra("position", -1)

        /*binding = QueryCharEditPageBinding.inflate(layoutInflater)
        setContentView(binding.root)*/

        val saveButton = findViewById<Button>(R.id.btnSave)
        saveButton.setOnClickListener{
            val characterDao = RoomAppDB.getAppDatabase(this)?.characterDao()
            characterDao?.update(creationSession.queryResults[position].id, binding.EditTextName.text.toString())
        }

        val homeButton = findViewById<Button>(R.id.btnHome2)
        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}