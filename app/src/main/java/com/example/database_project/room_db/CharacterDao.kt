package com.example.database_project.room_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//The Dao (Data Access Object) interface contains all queries to the database
@Dao
interface CharacterDao {

    //Only returns data from the Subject table (Simple query)
    @Query("SELECT * FROM subject ORDER BY id desc")
    fun getSubjectData(): List<SubjectEntity>?      //Returns a list of SubjectEntity type

    @Insert
    fun insertSubject(subject: SubjectEntity): Long //Returns the ids of subject entries

    //Uses 'insertSubject' to find the foreign keys need to connect to this table
    @Insert
    fun insertBuild(build: BuildEntity)

    @Insert
    fun insertFaction(faction: FactionEntity)

    //Gets data from both the Subject table and Build table
    @Query("SELECT * FROM subject inner join build on subject.id = build.subject_id")
    fun getSubjectWithBuild(): List<SubjectWithBuild>?
}