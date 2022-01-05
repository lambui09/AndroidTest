package com.android.pixelteam.buiduclamtest.data.remote.repository

import com.android.pixelteam.buiduclamtest.base.BaseRepository
import com.android.pixelteam.buiduclamtest.data.local.dao.WorkoutDao
import com.android.pixelteam.buiduclamtest.data.remote.api.ApiService
import com.android.pixelteam.buiduclamtest.di.IoDispatcher
import com.android.pixelteam.buiduclamtest.model.entity.Work
import com.android.pixelteam.buiduclamtest.ui.main.adapter.WorkoutModel
import com.android.pixelteam.buiduclamtest.utils.DataResult
import com.android.pixelteam.buiduclamtest.utils.enum.Common.mapDate
import com.android.pixelteam.buiduclamtest.utils.enum.Common.mapDayOfMonth
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
    private val workoutDao: WorkoutDao
) : WorkoutRepository, BaseRepository(coroutineDispatcher) {

    override suspend fun fetchWorkout(): DataResult<Unit> = withResultContext {
        val dayWithAssignments: List<Work> =
            apiService.fetchWorkouts().data.sortedBy(Work::numberDay)
        workoutDao.insert(dayWithAssignments)
        return@withResultContext
    }

    override fun streamWorkout(): Flow<List<WorkoutModel>> {
        return workoutDao.getWorkouts().map { convertListAssignmentToListWorkoutModel(it) }
    }

    private fun convertListAssignmentToListWorkoutModel(listWorks: List<Work>): List<WorkoutModel> {
        return listWorks.flatMap { dayInfo ->
            val workoutModels = mutableListOf<WorkoutModel>()
            for (assignment in dayInfo.assignments) {
                if (workoutModels.isEmpty()) {
                    val dayOfWeekName = mapDate[dayInfo.numberDay]!!
                    val dayOfMonthNumber = mapDayOfMonth[dayInfo.numberDay]!!
                    val model = WorkoutModel.DayAndJobModel(
                        dayOfWeek = dayOfWeekName,
                        dateOfWeek = dayOfMonthNumber, assignment = assignment
                    )
                    workoutModels.add(model)
                } else {
                    val model = WorkoutModel.OnlyJobModel(assignment = assignment)
                    workoutModels.add(model)
                }
            }
            workoutModels
        }
    }
}

interface WorkoutRepository {
    suspend fun fetchWorkout(): DataResult<Unit>
    fun streamWorkout(): Flow<List<WorkoutModel>>
}