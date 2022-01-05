package com.android.pixelteam.buiduclamtest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.android.pixelteam.buiduclamtest.data.local.dao.WorkoutDao
import com.android.pixelteam.buiduclamtest.model.entity.Work
import com.android.pixelteam.buiduclamtest.utils.converter.EntityConverter

@Database(
    exportSchema = false,
    entities = [Work::class],
    version = 1
)
@TypeConverters(EntityConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
}