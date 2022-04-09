package com.example.database_project.room_db

import androidx.room.Embedded
import androidx.room.Relation
import androidx.room.Transaction

//pretty much a set / get class for containing the information gained by joining the two tables in a query
data class SubjectWithBuild(var id: String, var name: String, var race: String, var hair: String, var ears: String, var eyes: String)

//Contains all of the unique data within the database
data class AllFourTablesJoined(var id: String, var name: String,                                        //Subject
                               var race: String, var hair: String, var ears: String, var eyes: String,  //Build
                               var faction_name: String, var reputation: String, var ideology: String,  //Faction
                               var class_name: String, var currency: Double                             //Class
)
