package com.example.database_project.char_query

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.database_project.R
import com.example.database_project.RecyclerAdapter
import com.example.database_project.databinding.ActivityQuerySearchResultsBinding

class QuerySearchResults : AppCompatActivity() {
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
        adapter = RecyclerAdapter()
        binding.contentMain.recyclerView.adapter = adapter


    }//end onCreate
}