package com.android.pixelteam.buiduclamtest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.pixelteam.buiduclamtest.data.local.dao.WorkoutDao
import com.android.pixelteam.buiduclamtest.model.entity.Work

@Database(
    exportSchema = false,
    entities = [Work::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
}