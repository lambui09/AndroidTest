package com.android.pixelteam.buiduclamtest.ui.main.adapter

import com.android.pixelteam.buiduclamtest.model.entity.Assignment
import com.android.pixelteam.buiduclamtest.model.entity.Work
/**
 * ta dang sua laij cai ni day, la ta tao 3 type
 * 1. co ngay co job
 * 2. cos ngay ma k co job
 * 3 chi co job k co ngay.
 * Ta handle con 1 cho api convert data sang cai workoutModel ni de submitList la xong.
 * Bua ta nop hom qua thay sai me roi, nen sua cho no chuan.
 * */
sealed class WorkoutModel {
    class DAY_AND_JOB_MODEL(val work: Work) : WorkoutModel()
    class DAY_NOT_JOB_MODEL(val work: Work) : WorkoutModel()
    class JOB_MODEL(val assignment: Assignment) : WorkoutModel()
}