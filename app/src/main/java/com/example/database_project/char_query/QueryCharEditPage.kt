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
import com.example.database_project.room_db.AllFourTablesJoined
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

        //Get which card cell was chosen and apply their data to the activity screen as appropriately
        val position = intent.getIntExtra("editPosition1", -1)

        val raceType = creationSession.queryResults[position].race
        val HairType = creationSession.queryResults[position].hair
        val EyeType = creationSession.queryResults[position].eyes
        val EarType = creationSession.queryResults[position].ears

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
//        val position = intent.getIntExtra("position", -1)

        //EditTextName
        binding.EditTextName.setText(creationSession.queryResults[position].name)

        //Hair ArrayAdapter
        val hairList = res.getStringArray(R.array.hair)
        val hairArrayAdapter = ArrayAdapter(this, R.layout.custom_spinner_item, hairList)
        binding.hairSpinner.adapter = hairArrayAdapter
        binding.hairSpinner.setSelection(hairArrayAdapter.getPosition(creationSession.queryResults[position].hair))
        binding.hairSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                binding.imageView16.setImageResource(resources.getIdentifier(
                    creationSession.setHairImage(binding.hairSpinner.selectedItem.toString()), "drawable", packageName))
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
                // slight change to push
            }
        })

        //Eyes ArrayAdapter
        val eyesList = res.getStringArray(R.array.eyes)
        val eyesArrayAdapter = ArrayAdapter(this, R.layout.custom_spinner_item, eyesList)
        binding.eyesSpinner.adapter = eyesArrayAdapter
        binding.eyesSpinner.setSelection(eyesArrayAdapter.getPosition(creationSession.queryResults[position].eyes))
        binding.eyesSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                binding.imageView15.setImageResource(resources.getIdentifier(
                    creationSession.setEyesImage(binding.eyesSpinner.selectedItem.toString()), "drawable", packageName))
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        })

        //Ears ArrayAdapter
        val earsList = res.getStringArray(R.array.ears)
        val earsArrayAdapter = ArrayAdapter(this, R.layout.custom_spinner_item, earsList)
        binding.earsSpinner.adapter = earsArrayAdapter
        binding.earsSpinner.setSelection(earsArrayAdapter.getPosition(creationSession.queryResults[position].ears))
        binding.earsSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                binding.imageView14.setImageResource(resources.getIdentifier(
                    creationSession.setEarsImage(binding.earsSpinner.selectedItem.toString()), "drawable", packageName))
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        })

        //Race ArrayAdapter
        val raceList = res.getStringArray(R.array.races)
        val raceArrayAdapter = ArrayAdapter(this, R.layout.custom_spinner_item, raceList)
        binding.raceSpinnerEdit.adapter = raceArrayAdapter
        binding.raceSpinnerEdit.setSelection(raceArrayAdapter.getPosition(creationSession.queryResults[position].race))
        binding.raceSpinnerEdit.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                binding.imageView5.setImageResource(resources.getIdentifier(
                    creationSession.setRaceImage(binding.raceSpinnerEdit.selectedItem.toString()), "drawable", packageName))
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        })

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
                getStartingCurrency(binding.classSpinnerEdit.selectedItem.toString())
                binding.actualCreditId2.text = creationSession.user_currency.toString()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        })

        val saveButton = findViewById<Button>(R.id.btnSave)
        saveButton.setOnClickListener{
            //Set values in creationSession
            creationSession.queryResults[position].hair = binding.hairSpinner.selectedItem.toString()
            creationSession.queryResults[position].race = binding.raceSpinnerEdit.selectedItem.toString()
            creationSession.queryResults[position].eyes = binding.eyesSpinner.selectedItem.toString()
            creationSession.queryResults[position].ears = binding.earsSpinner.selectedItem.toString()

            creationSession.queryResults[position].name = binding.EditTextName.text.toString()
            creationSession.queryResults[position].faction_name = binding.factionSpinnerEdit.selectedItem.toString()
            creationSession.queryResults[position].class_name = binding.classSpinnerEdit.selectedItem.toString()

            //Update Database values
            val characterDao = RoomAppDB.getAppDatabase(this)?.characterDao()
            characterDao?.updateSubject(creationSession.queryResults[position].id,
                                        creationSession.queryResults[position].name)
            characterDao?.updateBuild(  characterDao?.buildIDFromSubject(creationSession.queryResults[position].id),
                                        creationSession.queryResults[position].race,
                                        creationSession.queryResults[position].hair,
                                        creationSession.queryResults[position].ears,
                                        creationSession.queryResults[position].eyes)
            characterDao?.updateFaction(    characterDao?.factionIDFromSubject(creationSession.queryResults[position].id),
                                            creationSession.queryResults[position].faction_name,
                                            creationSession.queryResults[position].reputation,
                                            creationSession.queryResults[position].ideology,
            )
            characterDao?.updateClass(  characterDao?.classIDFromSubject(creationSession.queryResults[position].id),
                                        creationSession.queryResults[position].class_name,
                                        creationSession.queryResults[position].currency
            )

            //CustomModel
            CustomModel.instance?.setPosition(position)
            CustomModel.instance?.setEditedChar(creationSession.queryResults[position])
            CustomModel.instance?.changeState(true);

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

class CustomModel private constructor() {
    interface OnCustomStateListener {
        fun stateChanged(pos: Int, editedChar: AllFourTablesJoined)
    }

    private var mListener: OnCustomStateListener? = null
    private var position: Int = -1
    private var editedChar: AllFourTablesJoined? = null

    var state = false
        private set

    fun setPosition(position: Int){
        this.position = position
    }

    fun setEditedChar(editedChar: AllFourTablesJoined){
        this.editedChar = editedChar
    }

    fun setListener(listener: OnCustomStateListener?) {
        mListener = listener
    }

    fun changeState(state: Boolean) {
        if (mListener != null) {
            this.state = state
            notifyStateChange()
        }
    }

    private fun notifyStateChange() {
        mListener!!.stateChanged(position,   creationSession.queryResults[position])
    }

    companion object {
        private var mInstance: CustomModel? = null
        val instance: CustomModel?
            get() {
                if (mInstance == null) {
                    mInstance = CustomModel()
                }
                return mInstance
            }
    }
}

//
//interface OnEditPass {
//    fun onEditPass(pos: Int, editedChar: AllFourTablesJoined)
//}