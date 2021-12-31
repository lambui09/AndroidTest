package com.android.pixelteam.buiduclamtest.model.entity

import com.google.gson.annotations.SerializedName

/**
 * "_id": "5e65189e35c44f223f75e471",
"assignments": [
{
"_id": "5e6518b535c44f223f75e492",
"title": "Legs day",
"status": 1,
"total_exercise": 5
}
],
"day": 0
 * */
data class Work(
    @SerializedName("_id") val id: String?,
    @SerializedName("assignments") val list: List<Assignment>?,
    @SerializedName("day") val date: Int?,
)