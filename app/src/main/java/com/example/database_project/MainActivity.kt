package com.example.database_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.database_project.char_creator.CharActivity
import com.example.database_project.char_query.QueryPage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_page)

        //TODO: Using the database in main activity causes a runtime error
//        //Creates Database
//        var helper = MyHelper(applicationContext)
//        var db = helper.readableDatabase

       /* var helper = DatabaseHandler(applicationContext)
        var db = helper.readableDatabase*/

        val button = findViewById<Button>(R.id.CreateCharacterbtn)

        button.setOnClickListener{
            val intent = Intent(this, CharActivity::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.QueryPagebtn)

        button2.setOnClickListener{
            val intent = Intent(this, QueryPage::class.java)
            startActivity(intent)
        }

    }//end onCreate
}//end mainActivity