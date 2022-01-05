package com.android.pixelteam.buiduclamtest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.android.pixelteam.buiduclamtest.model.entity.Work
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM `workout`")
    fun getWorkouts(): Flow<List<Work>>

    @Insert(onConflict = REPLACE)
    fun insert(workoutList: List<Work>)
}