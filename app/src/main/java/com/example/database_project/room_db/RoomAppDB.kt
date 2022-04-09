package com.example.database_project.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//DON'T FORGET YOU HAVE TO ADD A REFERENCE TO EACH TABLE YOU ADD IN THE @Database PARAMETERS
@Database(entities = [SubjectEntity::class, BuildEntity::class, FactionEntity::class, ClassEntity::class], version = 3, exportSchema = true)
abstract class RoomAppDB : RoomDatabase(){

    //Returns the Dao
    abstract fun characterDao(): CharacterDao?

    //Create the database class
    companion object{
        private var INSTANCE : RoomAppDB? = null

        //TODO: Add migration function to allow us to add new tables to the DB
        var MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `faction` (`faction_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `subject_id` INTEGER NOT NULL, `reputation` TEXT NOT NULL, `ideology` TEXT NOT NULL, FOREIGN KEY(`subject_id`) REFERENCES `subject`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
                database.execSQL("CREATE TABLE IF NOT EXISTS `class` (`class_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `subject_id` INTEGER NOT NULL, `class_name` TEXT NOT NULL, `currency` REAL NOT NULL, FOREIGN KEY(`subject_id`) REFERENCES `subject`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
            }

        }

        //I forgot to add faction names to the table
        var MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'faction' ADD COLUMN `faction_name` TEXT NOT NULL DEFAULT 'Merchants Guild'")
                database.execSQL("CREATE TABLE IF NOT EXISTS `class` (`class_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `subject_id` INTEGER NOT NULL, `class_name` TEXT NOT NULL, `currency` REAL NOT NULL, FOREIGN KEY(`subject_id`) REFERENCES `subject`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
            }

        }

        fun getAppDatabase(context: Context): RoomAppDB? {
            if(INSTANCE == null) {
               INSTANCE = Room.databaseBuilder<RoomAppDB>(
                    context.applicationContext, RoomAppDB::class.java, "character_db"
                ).allowMainThreadQueries()
                   .addMigrations(MIGRATION_1_2)
                   .addMigrations(MIGRATION_2_3)
                   .build()
            }
            return  INSTANCE
        } //Returns INSTANCE variable
    }



}