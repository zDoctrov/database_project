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
        )
)
data class BuildEntity (@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "build_id") val build_id: Int,
                        @ColumnInfo(name = "subject_id") val id: Int,
                        @ColumnInfo(name = "race") val race: String,
                        @ColumnInfo(name = "hair") val hair: String,
                        @ColumnInfo(name = "ears") val ears: String,
                        @ColumnInfo(name = "eyes") val eyes: String
                        )