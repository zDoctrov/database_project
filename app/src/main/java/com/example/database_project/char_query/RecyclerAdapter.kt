package com.example.database_project

import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.bold
import androidx.core.text.underline
import androidx.recyclerview.widget.RecyclerView
import com.example.database_project.char_creator.creationSession
import com.example.database_project.room_db.AllFourTablesJoined


class RecyclerAdapter(private val listener: OnCharListener) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    val mutableQueryResults : MutableList<AllFourTablesJoined> = creationSession.queryResults.toMutableList()

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        //Image layering: Race --> Eyes --> Ear --> Hair
        var raceImage : ImageView
        var eyeImage : ImageView
        var earImage : ImageView
        var hairImage : ImageView

        var charName : TextView
        var charClass : TextView
        var charRace : TextView
        var charFaction : TextView


        init {
            raceImage = itemView.findViewById(R.id.raceImage)
            eyeImage = itemView.findViewById(R.id.eyeImage)
            earImage = itemView.findViewById(R.id.earImage)
            hairImage = itemView.findViewById(R.id.hairImage)

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

        // Race
        val raceImage = "@drawable/" + creationSession.setRaceImage(mutableQueryResults[position].race)
        val imageResource = App.context?.resources?.getIdentifier(raceImage, "drawable", App.context?.packageName)
        viewHolder.raceImage.setImageResource(imageResource!!)

        // Eyes
        val eyeImage = "@drawable/" + creationSession.setEyesImage(mutableQueryResults[position].eyes)
        val eyeImageResource = App.context?.resources?.getIdentifier(eyeImage, "drawable", App.context?.packageName)
        viewHolder.eyeImage.setImageResource(eyeImageResource!!)

        // Ear
        val earImage = "@drawable/" + creationSession.setEarsImage(mutableQueryResults[position].ears)
        val earImageResource = App.context?.resources?.getIdentifier(earImage, "drawable", App.context?.packageName)
        viewHolder.earImage.setImageResource(earImageResource!!)

        // Hair
        val hairImage = "@drawable/" + creationSession.setHairImage(mutableQueryResults[position].hair)
        val hairImageResource = App.context?.resources?.getIdentifier(hairImage, "drawable", App.context?.packageName)
        viewHolder.hairImage.setImageResource(hairImageResource!!)
    }

    fun deleteCharItem(pos: Int){
        Toast.makeText(App.context, "The character ${creationSession.queryResults[pos].name} was Deleted.", Toast.LENGTH_LONG).show()
        mutableQueryResults.removeAt(pos)
        notifyItemRemoved(pos)
        notifyItemRangeChanged(pos, mutableQueryResults.size)
        creationSession.queryResults = mutableQueryResults
    }

    override fun getItemCount(): Int {
        return mutableQueryResults.size
    }

    interface OnCharListener{
        fun onCharClick(pos : Int)
    }
}


