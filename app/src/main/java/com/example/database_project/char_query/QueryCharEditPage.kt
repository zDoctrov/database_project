package com.example.database_project.char_query



import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Update
import com.example.database_project.R
import com.example.database_project.databinding.QueryCharEditPageBinding

class QueryCharEditPage: AppCompatActivity() {
    private lateinit var binding: QueryCharEditPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.query_char_edit_page)

        val homeButton = findViewById<Button>(R.id.btnHome2)
        homeButton.setOnClickListener {
            finish()
        }

//        val saveButton = findViewById<Button>(R.id.btnSave)
//        saveButton.setOnClickListener{ view ->
//            update()
//        }
//
//    }//end on create
//
//    private fun update() {
//        TODO("Not yet implemented")
//    }
//
    }
}