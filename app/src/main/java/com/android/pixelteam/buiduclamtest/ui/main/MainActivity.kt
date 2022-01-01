package com.android.pixelteam.buiduclamtest.ui.main

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.pixelteam.buiduclamtest.R
import com.android.pixelteam.buiduclamtest.base.BaseActivity
import com.android.pixelteam.buiduclamtest.ui.main.adapter.WorkoutAdapter
import com.android.pixelteam.buiduclamtest.ui.main.adapter.WorkoutModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>(MainViewModel::class) {
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = WorkoutAdapter()
        initView(adapter)
        observer(adapter)
    }

    private fun initView(workoutAdapter: WorkoutAdapter) {
        viewModel.fetchWorkouts()
        findViewById<RecyclerView>(R.id.rvWorkout).run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = workoutAdapter
            addItemDecoration(DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL))
        }
    }
    private fun observer(workoutAdapter: WorkoutAdapter) {
        val listWorkout = MutableList<WorkoutModel>()
        with(viewModel) {
            workouts.observe(this@MainActivity, {
                it?.let {
                    it.forEach { item ->
                        listWorkout.add(item)
                    }
                }
                workoutAdapter.submitList(it)
            })
        }
    }
}