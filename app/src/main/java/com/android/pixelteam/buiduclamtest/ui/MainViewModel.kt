package com.android.pixelteam.buiduclamtest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.pixelteam.buiduclamtest.base.BaseVM
import com.android.pixelteam.buiduclamtest.data.remote.repository.WorkoutRepository
import com.android.pixelteam.buiduclamtest.model.entity.Work
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository
) : BaseVM(){
    val _workouts = MutableLiveData<List<Work>>()
    val workouts: LiveData<List<Work>>
        get() = _workouts

    fun fetchWorkouts() {

    }
}