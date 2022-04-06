package com.example.database_project.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SubjectEntity::class, BuildEntity::class], version = 1)
abstract class RoomAppDB : RoomDatabase(){

    //Returns the Dao
    abstract fun characterDao(): CharacterDao?

    //Create the database class
    companion object{
        private var INSTANCE : RoomAppDB? = null

        fun getAppDatabase(context: Context): RoomAppDB? {
            if(INSTANCE == null) {
               INSTANCE = Room.databaseBuilder<RoomAppDB>(
                    context.applicationContext, RoomAppDB::class.java, "character_db"
                ).allowMainThreadQueries()
                   .build()
            }
            return  INSTANCE
        } //Returns INSTANCE variable
    }
}