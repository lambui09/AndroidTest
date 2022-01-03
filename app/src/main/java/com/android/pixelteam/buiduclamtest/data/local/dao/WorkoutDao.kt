package com.android.pixelteam.buiduclamtest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.pixelteam.buiduclamtest.model.entity.Work

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM `workout`")
    suspend fun getWorkouts(): List<Work>
    @Insert
    suspend fun insert(workoutList: List<Work>)
}