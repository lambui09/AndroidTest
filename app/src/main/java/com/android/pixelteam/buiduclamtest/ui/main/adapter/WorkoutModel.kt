package com.android.pixelteam.buiduclamtest.ui.main.adapter

import com.android.pixelteam.buiduclamtest.model.entity.Assignment

sealed class WorkoutModel {
    data class DayAndJobModel(
        val dayOfWeek: String,
        val dateOfWeek: String,
        val assignment: Assignment
    ) : WorkoutModel()

    data class DayNotJobModel(
        val dayOfWeek: String,
        val dateOfWeek: String,
    ) : WorkoutModel()

    data class OnlyJobModel(val assignment: Assignment) : WorkoutModel()
}