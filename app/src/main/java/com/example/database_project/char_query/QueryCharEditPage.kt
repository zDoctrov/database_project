package com.example.database_project.char_query



import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Update
import com.example.database_project.R
import com.example.database_project.char_creator.creationSession
import com.example.database_project.databinding.QueryCharEditPageBinding
import com.example.database_project.room_db.RoomAppDB

class QueryCharEditPage: AppCompatActivity() {

    private lateinit var binding: QueryCharEditPageBinding
    //Set up binding, so you can reference the ID's of things on this activity's layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.query_char_edit_page)

        //Get which card cell was chosen and apply their data to the activity screen as appropriately
        val position = intent.getIntExtra("position", -1)

        binding = QueryCharEditPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val saveButton = findViewById<Button>(R.id.btnSave)
        saveButton.setOnClickListener{
            val characterDao = RoomAppDB.getAppDatabase(this)?.characterDao()
            characterDao?.update(creationSession.queryResults[position].id, binding.EditTextName.text.toString())
        }

        val homeButton = findViewById<Button>(R.id.btnHome2)
        homeButton.setOnClickListener {
            finish()
        }


    }
}