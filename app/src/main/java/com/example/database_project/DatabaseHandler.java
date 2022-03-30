package com.example.database_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "CharacterCreator.db";
    private static final int DATABASE_VERSION =1;

    //table Subject
    private static final String TABLE_NAME = "Subject";
    private static final String COLUMN_ID = "SubjectID";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_BUILD = "Build";
    private static final String COLUMN_FACTION = "Faction";
    private static final String COLUMN_CLASS = "Class";

    //table Build
    private static final String TABLE_BUILD_NAME = "Build";
    private static final String COLUMN_BUILD_RACE = "Race";
    private static final String COLUMN_BUILD_HAIR = "Hair";
    private static final String COLUMN_BUILD_EARS = "Class";
    private static final String COLUMN_BUILD_EYES = "Eyes";

    //table Faction
    private static final String TABLE_FACTION_NAME = "Faction";
    private static final String COLUMN_FACTION_NAME = "Fac_Name";
    private static final String COLUMN_FACTION_REPUTATION = "Reputation";
    private static final String COLUMN_FACTION_IDEAOLOGY = "Class";

    //table Status
    private static final String TABLE_STATUS_NAME = "Status";
    private static final String COLUMN_STATUS_ID = "StatusID";
    private static final String COLUMN_CREDIT = "Credit ($)";



    //constuctor
    public DatabaseHandler(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BUILD_HAIR + " TEXT, " +
                COLUMN_BUILD_EARS + " TEXT, " +
                COLUMN_BUILD_EYES + " TEXT);";
        String query2 = "CREATE TABLE " + TABLE_BUILD_NAME + " (" +
                COLUMN_BUILD_RACE + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_BUILD + " TEXT, " +
                COLUMN_FACTION + " TEXT, " +
                COLUMN_CLASS + " TEXT);";
        String query3 = "CREATE TABLE " + TABLE_FACTION_NAME + " (" +
                COLUMN_FACTION_NAME + " TEXT, " +
                COLUMN_FACTION_REPUTATION + " TEXT, " +
                COLUMN_FACTION_IDEAOLOGY + " TEXT);";
        String query4 = "CREATE TABLE " + TABLE_STATUS_NAME + " (" +
                COLUMN_STATUS_ID + " TEXT, " +
                COLUMN_CREDIT + " INTEGER);";
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // MAY NEED TO ADD OTHER TABLES HERE
        onCreate(db);
    }
}