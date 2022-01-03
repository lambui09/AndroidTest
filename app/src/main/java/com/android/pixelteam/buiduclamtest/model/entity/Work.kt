package com.android.pixelteam.buiduclamtest.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "workout")
data class Work(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    @SerializedName("_id") val id: String?,
    @ColumnInfo(name = "assignments")
    @SerializedName("assignments") val assignments: List<Assignment>,
    @ColumnInfo(name = "day")
    @SerializedName("day") val numberDay: Int
)