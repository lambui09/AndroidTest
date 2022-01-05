package com.android.pixelteam.buiduclamtest.utils.converter

import androidx.room.TypeConverter
import com.android.pixelteam.buiduclamtest.model.entity.Assignment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EntityConverter {
    inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object : TypeToken<T>() {}.type)
    val gson = Gson()
    @TypeConverter
    fun listAssignmentsToString(assignments: List<Assignment>): String {
        return gson.toJson(assignments)
    }

    @TypeConverter
    fun stringToListAssignment(json: String): List<Assignment> {
        return gson.fromJson(json)
    }
}