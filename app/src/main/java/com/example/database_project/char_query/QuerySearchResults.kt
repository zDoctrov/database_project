package com.example.database_project.char_query

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.database_project.R
import com.example.database_project.RecyclerAdapter
import com.example.database_project.databinding.ActivityQuerySearchResultsBinding
import com.example.database_project.room_db.AllFourTablesJoined

class QuerySearchResults : AppCompatActivity(), RecyclerAdapter.OnCharListener, CustomModel.OnCustomStateListener {
    private lateinit var binding: ActivityQuerySearchResultsBinding

    private var layoutManager : RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query_search_results)

        binding = ActivityQuerySearchResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Use CustomModel to communicate with QueryCharEditPage
        CustomModel.instance?.setListener(this)
        val modelState: Boolean = CustomModel.instance?.state!!
        Log.d(TAG, "Current state: $modelState")

        layoutManager = LinearLayoutManager(this)

        binding.contentMain.recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter(this)

        binding.contentMain.recyclerView.adapter = adapter


    }//end onCreate

    //Need to be able to have this Activity communicate with the RecyclerAdapter it houses (To add an Intent transition from clicking a Card Instance)
    override fun onCharClick(pos: Int) {
        val intent = Intent(this, InstanceResultPage::class.java)
            .putExtra("position", pos)
        println("QuerySearchResults --> onCharClick pos: $pos")

        //Use startActivityForResult to get data back from InstanceResultPage and
        // redirect it toward the RecycleView after updating or deleting an item from DB
        //  requestCode = 1
        startActivityForResult(intent, 1)
    }//end onCharClick

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //Delete (request code = 1)
        if(requestCode == 1){
                if(resultCode == RESULT_OK){
                    //Do data stuff here
                    var receivedPosition = data?.getIntExtra("deletePosition", 0)

                    //Access the adapter's functions using this bizarre syntax
                    (this.adapter as RecyclerAdapter).deleteCharItem(receivedPosition!!)
                }
        }
    }

    override fun stateChanged(pos: Int, editedChar: AllFourTablesJoined) {
        val modelState: Boolean = CustomModel.instance?.state!!
        Log.d(TAG, "MainActivity says: Model state changed: $modelState")
        Log.d(TAG, "MainActivity says: Model state changed: $pos\n $editedChar")
        (this.adapter as RecyclerAdapter).editCharItem(pos, editedChar)
    }

    //TODO: Get data back from QueryCharEditPage so that the recycleView can be updated
//    override fun onEditPass(pos: Int, editedChar: AllFourTablesJoined) {
//        (this.adapter as RecyclerAdapter).editCharItem(pos, editedChar)
//    }
}