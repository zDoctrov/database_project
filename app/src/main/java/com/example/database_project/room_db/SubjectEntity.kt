package com.example.database_project.room_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "subject")
data class SubjectEntity (@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
                          @ColumnInfo(name = "name") val name: String
                          )

@Entity(tableName = "build",
        foreignKeys = arrayOf(
            ForeignKey(entity = SubjectEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("subject_id"),
            onDelete = ForeignKey.CASCADE)
        )//end foreignKeys
)//tableName build
data class BuildEntity (@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "build_id") val build_id: Int,
                        @ColumnInfo(name = "subject_id") val id: Int,
                        @ColumnInfo(name = "race") val race: String,
                        @ColumnInfo(name = "hair") val hair: String,
                        @ColumnInfo(name = "ears") val ears: String,
                        @ColumnInfo(name = "eyes") val eyes: String
                        )//end BuildEntity


@Entity(tableName = "faction",
    foreignKeys = arrayOf(
        ForeignKey(entity = SubjectEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("subject_id"),
            onDelete = ForeignKey.CASCADE)
    )//end foreignKeys
)//end faction table
data class FactionEntity (@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "faction_id") val faction_id: Int,
                        @ColumnInfo(name = "subject_id") val id: Int,
                        @ColumnInfo(name = "reputation") val reputation: String,
                        @ColumnInfo(name = "ideology") val ideology: String
                        )//end FactionEntity

@Entity(tableName = "class",
    foreignKeys = arrayOf(
        ForeignKey(entity = SubjectEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("subject_id"),
            onDelete = ForeignKey.CASCADE)
    )//end foreignKeys
)//end faction table

data class ClassEntity (@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "class_id") val class_id: Int,
                          @ColumnInfo(name = "subject_id") val id: Int,
                          @ColumnInfo(name = "class_name") val class_name: String,
                          @ColumnInfo(name = "currency") val currency: Double

)//end StatusEntity


