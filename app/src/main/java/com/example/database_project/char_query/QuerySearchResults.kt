package com.example.database_project.char_query

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.database_project.R
import com.example.database_project.RecyclerAdapter
import com.example.database_project.databinding.ActivityQuerySearchResultsBinding

class QuerySearchResults : AppCompatActivity(), RecyclerAdapter.OnCharListener {
    private lateinit var binding: ActivityQuerySearchResultsBinding

    private var layoutManager : RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query_search_results)

        binding = ActivityQuerySearchResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        layoutManager = LinearLayoutManager(this)

        binding.contentMain.recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter(this)
        binding.contentMain.recyclerView.adapter = adapter

    }//end onCreate

    //Need to be able to have this Activity communicate with the RecyclerAdapter it houses (To add an Intent transition from clicking a Card Instance)
    override fun onCharClick(pos: Int) {
        val intent = Intent(this, InstanceResultPage::class.java)
            .putExtra("position", pos)
        startActivity(intent)
    }//end onCharClick
}