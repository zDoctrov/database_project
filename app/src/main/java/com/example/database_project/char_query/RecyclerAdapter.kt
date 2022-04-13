package com.example.database_project

import android.content.Intent
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.text.bold
import androidx.core.text.underline
import androidx.recyclerview.widget.RecyclerView
import com.example.database_project.char_creator.CharActivity3
import com.example.database_project.char_creator.creationSession
import com.example.database_project.char_query.InstanceResultPage
import com.example.database_project.char_query.QuerySearchResults
import com.example.database_project.room_db.AllFourTablesJoined
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text


class RecyclerAdapter(private val listener: OnCharListener) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    val mutableQueryResults : MutableList<AllFourTablesJoined> = creationSession.queryResults.toMutableList()

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var itemImage : ImageView
        var charName : TextView
        var charClass : TextView
        var charRace : TextView
        var charFaction : TextView


        init {
            itemImage = itemView.findViewById(R.id.itemImage)
            charName = itemView.findViewById(R.id.characterName)
            charClass = itemView.findViewById(R.id.characterClass)
            charRace = itemView.findViewById(R.id.characterRace)
            charFaction = itemView.findViewById(R.id.characterFaction)

            itemView.setOnClickListener {v : View ->
                var position : Int = getAdapterPosition()

                //Interface allows us to use any passed Activity as the owner
                //  If statement to prevent deleted items from being clicked (Just in case)
                if (position !=RecyclerView.NO_POSITION){
                    listener.onCharClick(position)
                }
            }

        }//end init

    }//end inner class

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_layout, viewGroup, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: RecyclerAdapter.ViewHolder, position: Int) {
        viewHolder.charName.text = SpannableStringBuilder()
            .underline {  bold{ append("Name") }  }
            .bold{append(":")}
            .append(" " + mutableQueryResults[position].name)

        viewHolder.charRace.text =  SpannableStringBuilder()
            .bold{append("Race: ")}
            .append(mutableQueryResults[position].race)

        viewHolder.charClass.text = SpannableStringBuilder()
            .bold{append("Class: ")}
            .append(mutableQueryResults[position].class_name)

        viewHolder.charFaction.text = SpannableStringBuilder()
            .bold{append("Faction: ")}
            .append(mutableQueryResults[position].faction_name)

        //TODO: Image profiles for each character
        viewHolder.itemImage.setImageResource(R.drawable.nft_monkey)
    }

    fun deleteCharItem(pos: Int){
        mutableQueryResults.removeAt(pos)
        notifyItemRemoved(pos)
        notifyItemRangeChanged(pos, mutableQueryResults.size)
    }

    override fun getItemCount(): Int {
        return mutableQueryResults.size
    }

    interface OnCharListener{
        fun onCharClick(pos : Int)
    }
}


