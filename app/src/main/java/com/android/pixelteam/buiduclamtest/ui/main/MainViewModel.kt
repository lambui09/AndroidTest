package com.android.pixelteam.buiduclamtest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.pixelteam.buiduclamtest.base.BaseVM
import com.android.pixelteam.buiduclamtest.data.remote.repository.WorkoutRepository
import com.android.pixelteam.buiduclamtest.model.entity.Work
import com.android.pixelteam.buiduclamtest.ui.main.adapter.WorkoutModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository
) : BaseVM(){
    val _workouts = MutableLiveData<List<Work>>()
    val listWorkout = MutableLiveData<List<WorkoutModel>>()
    val workouts: LiveData<List<Work>>
        get() = _workouts

    fun fetchWorkouts() {
        viewModelScope(_workouts) {
            workoutRepository.fetchWorkout()
        }
    }
}