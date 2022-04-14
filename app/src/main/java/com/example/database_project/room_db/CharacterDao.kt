package com.example.database_project.room_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*

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
    fun getAllFourTablesJoined(): List<AllFourTablesJoined>?    //No Filters

    //Queries for getting the subject id from foreign keys
    @Query("SELECT build_id FROM build WHERE subject_id = :subject_id")
    fun buildIDFromSubject(subject_id: Int?): Int

    @Query("SELECT faction_id FROM faction WHERE subject_id = :subject_id")
    fun factionIDFromSubject(subject_id: Int?): Int

    @Query("SELECT class_id FROM class WHERE subject_id = :subject_id")
    fun classIDFromSubject(subject_id: Int?): Int

    //Filtered Version: Parameters are 'NULL' if user doesn't set a value for filtering
    @Query("""
        SELECT * FROM subject
        inner join build on subject.id = build.subject_id
        inner join faction on subject.id = faction.subject_id
        inner join class on subject.id = class.subject_id
        WHERE
        (:name IS NULL OR name LIKE '%' || :name || '%') AND
        (:race IS NULL OR race LIKE '%' || :race || '%') AND
        (:faction_name IS NULL OR faction_name LIKE '%' || :faction_name || '%') AND
        (:hair IS NULL OR hair LIKE '%' || :hair || '%') AND
        (:ears IS NULL OR ears LIKE '%' || :ears || '%') AND
        (:eyes IS NULL OR eyes LIKE '%' || :eyes || '%') AND
        (:class_name IS NULL OR class_name LIKE '%' || :class_name || '%')
    """)
    fun getFilteredFourTablesJoined(name: String?,
                                    race: String?,
                                    faction_name: String?,
                                    hair: String?,
                                    ears: String?,
                                    eyes: String?,
                                    class_name: String?
                                    ): List<AllFourTablesJoined>?    //No Filters

    //Query Type #3: Update

//    @Entity(tableName = "subject")
//data class SubjectEntity (@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
//                          @ColumnInfo(name = "name") val name: String
//                          )
    //Subject Update
    @Query("UPDATE subject SET name = :name WHERE id = :subject_id")
    fun updateSubject(subject_id: Int?, name: String?)

    //Build Update
    @Query("UPDATE build SET race = :race, hair = :hair, ears = :ears, eyes = :eyes WHERE build_id = :build_id")
    fun updateBuild(build_id: Int?,
                    race: String?,
                    hair: String?,
                    ears: String?,
                    eyes: String?
)

    //Faction Update
    @Query("UPDATE faction SET faction_name = :faction_name, reputation = :reputation, ideology = :ideology WHERE faction_id = :faction_id")
    fun updateFaction(faction_id: Int?,
                      faction_name: String?,
                      reputation: String?,
                      ideology: String?)

    //Class Update
    @Query("UPDATE class SET class_name = :class_name, currency = :currency WHERE class_id = :class_id")
    fun updateClass(class_id: Int?,
                    class_name: String?,
                    currency: Double?)




    //Query Type #4: Delete
    @Query("DELETE FROM subject WHERE id = :subject_id")
    fun delete(subject_id: Int?)
}