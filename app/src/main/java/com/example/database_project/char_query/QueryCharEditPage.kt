package com.example.database_project.char_query



import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.database_project.MainActivity
import com.example.database_project.R
import com.example.database_project.char_creator.creationSession
import com.example.database_project.databinding.QueryCharEditPageBinding
import com.example.database_project.room_db.RoomAppDB

class QueryCharEditPage: AppCompatActivity() {

    private lateinit var binding: QueryCharEditPageBinding
    //Set up binding, so you can reference the ID's of things on this activity's layout

//    lateinit var editPasser: OnEditPass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.query_char_edit_page)
        val res: Resources = resources
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

        //EditTextName
        binding.EditTextName.setText(creationSession.queryResults[position].name)

        //Race ArrayAdapter
        val raceList = res.getStringArray(R.array.races)
        val raceArrayAdapter = ArrayAdapter(this, R.layout.custom_spinner_item, raceList)
        binding.raceSpinnerEdit.adapter = raceArrayAdapter
        binding.raceSpinnerEdit.setSelection(raceArrayAdapter.getPosition(creationSession.queryResults[position].race))

        //Faction ArrayAdapter
        val factionList = res.getStringArray(R.array.factions)
        val factionArrayAdapter = ArrayAdapter(this, R.layout.custom_spinner_item, factionList)
        binding.factionSpinnerEdit.adapter = factionArrayAdapter
        binding.factionSpinnerEdit.setSelection(factionArrayAdapter.getPosition(creationSession.queryResults[position].faction_name))

        //Class ArrayAdapter
        val classList = res.getStringArray(R.array.user_class)
        val classArrayAdapter = ArrayAdapter(this, R.layout.custom_spinner_item, classList)
        binding.classSpinnerEdit.adapter = classArrayAdapter
        binding.classSpinnerEdit.setSelection(classArrayAdapter.getPosition(creationSession.queryResults[position].class_name))

        binding.classSpinnerEdit.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                // your code here
                getStartingCurrency(binding.classSpinnerEdit.selectedItem.toString())
                binding.actualCreditId2.text = creationSession.user_currency.toString()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
                // slight change to push
            }
        })

        val saveButton = findViewById<Button>(R.id.btnSave)
        saveButton.setOnClickListener{
            //Update Database values
            val characterDao = RoomAppDB.getAppDatabase(this)?.characterDao()
            characterDao?.update(creationSession.queryResults[position].id, binding.EditTextName.text.toString())

            //Set values in creationSession
            creationSession.queryResults[position].name = binding.EditTextName.text.toString()
            creationSession.queryResults[position].race = binding.raceSpinnerEdit.selectedItem.toString()
            creationSession.queryResults[position].faction_name = binding.factionSpinnerEdit.selectedItem.toString()
            creationSession.queryResults[position].class_name = binding.classSpinnerEdit.selectedItem.toString()

            //
//            editPasser.onEditPass(position, creationSession.queryResults[position])
//            val intent = Intent()
//            intent.putExtra("ActivityResult", "<Data to return>")
//            setResult(RESULT_OK, intent)
//            finish()

            val data = Intent(applicationContext, QuerySearchResults::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .putExtra("editPosition2", position)
            // Activity finished ok, return the data
            setResult(RESULT_OK, data)
            startActivity(data)
        }

        val homeButton = findViewById<Button>(R.id.btnHome2)
        homeButton.setOnClickListener {
            //Sends user back to start screen
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }


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
//
//interface OnEditPass {
//    fun onEditPass(pos: Int, editedChar: AllFourTablesJoined)
//}