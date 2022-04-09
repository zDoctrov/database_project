package com.example.database_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.database_project.char_creator.creationSession
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text


class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val titles = arrayOf("Chapter One", "Chapter Two", "Chapter Three",
    "Chapter Four", "Chapter Five", "Chapter Six", "Chapter Seven", "Chapter Eight")

    private val details = arrayOf("Item one details", "Item two details", "Item three details",
        "Item four details", "Item five details", "Item six details",
        "Item seven details", "Item eight details")

    private val images = intArrayOf(
        R.drawable.android_image_1, R.drawable.android_image_2,
        R.drawable.android_image_3, R.drawable.android_image_4, R.drawable.android_image_5,
        R.drawable.android_image_6, R.drawable.android_image_7, R.drawable.android_image_8)

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var itemImage : ImageView
        var charName : TextView
        var charRace : TextView
        var charFaction : TextView

        init {
            itemImage = itemView.findViewById(R.id.itemImage)
            charName = itemView.findViewById(R.id.characterName)
            charRace = itemView.findViewById(R.id.characterRace)
            charFaction = itemView.findViewById(R.id.characterFaction)

            itemView.setOnClickListener {v : View ->
                var position : Int = getAdapterPosition()

                Snackbar.make(v, "Click detected on item $position",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show()

            }
        }//end init

    }//end inner class

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_layout, viewGroup, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: RecyclerAdapter.ViewHolder, position: Int) {
        viewHolder.charName.text = "Name: " + creationSession.queryResults[position].name
        viewHolder.charRace.text = "Race: " + creationSession.queryResults[position].race
        viewHolder.charFaction.text = "Faction: " + creationSession.queryResults[position].faction_name

        //TODO: Image profiles for each character
        viewHolder.itemImage.setImageResource(R.drawable.android_image_1)
    }

    override fun getItemCount(): Int {
        return creationSession.queryResults.size
    }
}