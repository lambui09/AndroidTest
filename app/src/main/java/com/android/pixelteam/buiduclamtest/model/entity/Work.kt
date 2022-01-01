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
    @SerializedName("day") val numberDay: Int?,
    val date: String?,
    val dayOfWeek: String?
) {
    //after handle dymamic.
    val mapDate: HashMap<Int, String> = hashMapOf(
        0 to "MON",
        1 to "TUE",
        2 to "WEB",
        3 to "THU",
        4 to "FRI",
        5 to "SAT",
        6 to "SUN",
    )
    val mapDayOfWeek: HashMap<Int, String> = hashMapOf(
        0 to "20",
        1 to "21",
        2 to "22",
        3 to "23",
        4 to "24",
        5 to "25",
        6 to "26",
    )

    fun convertDay(work: Work) =
        Work(id, list, numberDay, date = mapDate[numberDay], dayOfWeek = mapDayOfWeek[numberDay])
}