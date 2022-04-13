package com.example.database_project.char_query

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
//            val characterDao = RoomAppDB.getAppDatabase(this)?.characterDao()
//            characterDao?.update(creationSession.queryResults[position].id, "Hubert")
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