package com.example.database_project.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//MyHelper class allows any Activity to access this database by passing the "Context" parameter to that activity
//If you make changes to you DB schema, increment the version number to see the effects
class MyHelper(context: Context) : SQLiteOpenHelper(context, "CharacterDB", null, 2){
    override fun onCreate(db: SQLiteDatabase?) {

        //TODO: test to see if this schema works
        db?.execSQL("PRAGMA foreign_keys=ON")

        //Create Build Table
        db?.execSQL(
            "CREATE TABLE Build(" +
                    "_id INTEGER primary key autoincrement, " +
                    "Race TEXT, " +
                    "Hair TEXT, " +
                    "Ears TEXT, " +
                    "Eyes TEXT" +
                    ")"
        )

        //Create Faction Table
        db?.execSQL(
            "CREATE TABLE Faction(" +
                    "_id INTEGER primary key autoincrement, " +
                    "Reputation TEXT, " +
                    "Ideology TEXT" +
                    ")"
        )

        //Create Class Table
        db?.execSQL(
            "CREATE TABLE Class(" +
                    "_id INTEGER primary key autoincrement, " +
                    "Credit REAL" +
                    ")"
        )

        //Create Subject Table (Contains all foreign keys)
        db?.execSQL(
            "CREATE TABLE Subject(" +
                    "_id INTEGER primary key autoincrement, " +
                    "Name TEXT, " +
                    "FOREIGN KEY(build) REFERENCES Build(_id), " +
                    "FOREIGN KEY(faction) REFERENCES Faction(_id), " +
                    "FOREIGN KEY(class) REFERENCES Class(_id)" +
                    ")"
        )


        //Insert 3 records (Since _id uses autoincrement, you don't need to insert it as its done automatically
        db?.execSQL("INSERT INTO SUBJECT(NAME)VALUES('Zac')")
        db?.execSQL("INSERT INTO SUBJECT(NAME)VALUES('Mac')")
        db?.execSQL("INSERT INTO SUBJECT(NAME)VALUES('Jack')")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        db!!.execSQL("DROP TABLE IF EXISTS")
//        onCreate(db)
    }

}