package com.android.pixelteam.buiduclamtest.model.entity

import com.google.gson.annotations.SerializedName
data class Work(
    @SerializedName("_id") val id: String?,
    @SerializedName("assignments") val assignments: List<Assignment>,
    @SerializedName("day") val numberDay: Int,
    val date: String?,
    val dayOfWeek: String?
)