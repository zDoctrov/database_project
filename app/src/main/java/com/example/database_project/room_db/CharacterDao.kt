package com.example.database_project.room_db

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery

//The Dao (Data Access Object) interface contains all queries to the database
@Dao
interface CharacterDao {

    //Query Type #1: INSERT
    //  'insertSubject' returns the ids of subject entries, which is used by the rest of the insert queries to find the foreign keys need to connect to this table
    @Insert
    fun insertSubject(subject: SubjectEntity): Long
    @Insert
    fun insertBuild(build: BuildEntity)
    @Insert
    fun insertFaction(faction: FactionEntity)
    @Insert
    fun insertClass(char_class: ClassEntity)

    //Query Type #2: SELECT (Static)
    //  This is the default query ran when no search filters are placed, resulting in a selection of EVERY character
    //  *Note that "inner join" removes Foreign Keys that aren't present in all tables (Pretty much entries entered before the addition of later tables)
    @Query("SELECT *" +
            " FROM subject" +
            " inner join build on subject.id = build.subject_id" +
            " inner join faction on subject.id = faction.subject_id" +
            " inner join class on subject.id = class.subject_id"
    )
    fun getAllFourTablesJoined(): List<AllFourTablesJoined>?

//    //Use "@RawQuery" to use variables within your query that allows you to filter the results
//    @RawQuery
//    fun getBooks(query: SupportSQLiteQuery?): List<AllFourTablesJoined>?



//    //Gets data from both the Subject table and Build table
//    @Query("SELECT * FROM subject inner join build on subject.id = build.subject_id")
//    fun getSubjectWithBuild(): List<SubjectWithBuild>?
//
//    //Only returns data from the Subject table (Simple query)
//    @Query("SELECT * FROM subject ORDER BY id desc")
//    fun getSubjectData(): List<SubjectEntity>?      //Returns a list of SubjectEntity type

    //Query Type #3: Update

    //Query Type #4: Delete
    @Query("DELETE FROM subject WHERE id = :subject_id")
    fun delete(subject_id: Int?)
}