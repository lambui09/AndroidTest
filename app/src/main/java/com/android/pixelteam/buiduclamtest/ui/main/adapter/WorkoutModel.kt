package com.android.pixelteam.buiduclamtest.ui.main.adapter

import com.android.pixelteam.buiduclamtest.model.entity.Assignment
import com.android.pixelteam.buiduclamtest.model.entity.Work

sealed class WorkoutModel {
    class DAY_AND_JOB_MODEL(val work: Work) : WorkoutModel()
    class DAY_NOT_JOB_MODEL(val work: Work) : WorkoutModel()
    class JOB_MODEL(val assignment: Assignment) : WorkoutModel()
}