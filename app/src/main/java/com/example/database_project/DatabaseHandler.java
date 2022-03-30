package com.example.database_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    public Context context;
    public static final String DATABASE_NAME = "CharacterCreator.db";
    public static final int DATABASE_VERSION =1;

    //table Subject
    public static final String TABLE_NAME = "Subject";
    public static final String COLUMN_ID = "SubjectID";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_BUILD = "Build";
    public static final String COLUMN_FACTION = "Faction";
    public static final String COLUMN_CLASS = "Class";

    //table Build
    public static final String TABLE_BUILD_NAME = "Build";
    public static final String COLUMN_BUILD_RACE = "Race";
    public static final String COLUMN_BUILD_HAIR = "Hair";
    public static final String COLUMN_BUILD_Ears = "Class";
    public static final String COLUMN_BUILD_EYES = "Eyes";

    //table Faction
    public static final String TABLE_FACTION_NAME = "Faction";
    public static final String COLUMN_FACTION_NAME = "Fac_Name";
    public static final String COLUMN_FACTION_REPUTATION = "Reputation";
    public static final String COLUMN_FACTION_IDEAOLOGY = "Class";

    //table Status
    public static final String TABLE_STATUS_NAME = "Status";
    public static final String COLUMN_STATUS_ID = "StatusID";
    public static final String COLUMN_CREDIT = "Credit ($)";



    //constuctor
    public DatabaseHandler(@Nullable Context context ) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}