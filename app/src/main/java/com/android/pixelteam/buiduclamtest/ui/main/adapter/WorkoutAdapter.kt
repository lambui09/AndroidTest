package com.android.pixelteam.buiduclamtest.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pixelteam.buiduclamtest.R

class WorkoutAdapter : ListAdapter<WorkoutModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.day_and_job_item_view -> DayAndWorkViewHolder(v)
            R.layout.day_not_job_item_view -> DayNotWorkViewHolder(v)
            R.layout.assignment_item_view -> WorkViewHolder(v)
            else -> throw IllegalStateException("Unknown view")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is DayAndWorkViewHolder -> holder.binData(item as WorkoutModel.DAY_AND_JOB_MODEL)
            is DayNotWorkViewHolder -> holder.binData(item as WorkoutModel.DAY_NOT_JOB_MODEL)
            is WorkViewHolder -> holder.binData(item as WorkoutModel.JOB_MODEL)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is WorkoutModel.DAY_AND_JOB_MODEL -> R.layout.day_and_job_item_view
            is WorkoutModel.DAY_NOT_JOB_MODEL -> R.layout.day_not_job_item_view
            is WorkoutModel.JOB_MODEL -> R.layout.assignment_item_view
            else -> throw IllegalStateException("Unknown view")
        }
    }

    inner class DayAndWorkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvDate: AppCompatTextView = itemView.findViewById(R.id.tvDate)
        private var tvDay: AppCompatTextView = itemView.findViewById(R.id.tvDay)
        private var tvTitle: AppCompatTextView = itemView.findViewById(R.id.tvTitle)
        private var tvJob: AppCompatTextView = itemView.findViewById(R.id.tvJob)
        fun binData(dayAndJob: WorkoutModel.DAY_AND_JOB_MODEL) {
            dayAndJob.work.let {
                tvDate.text = it.date
                tvDay.text = it.dayOfWeek
                it.list?.get(0)?.let {
                    tvTitle.text = it.title
                    tvJob.text = "${it.totalExercise} exercises"
                }
            }
        }
    }

    inner class DayNotWorkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvDate: AppCompatTextView = itemView.findViewById(R.id.tvDate)
        private var tvDay: AppCompatTextView = itemView.findViewById(R.id.tvDay)
        fun binData(dayNotWork: WorkoutModel.DAY_NOT_JOB_MODEL) {
            dayNotWork.work.let {
                tvDate.text = it.date
                tvDay.text = it.dayOfWeek
            }
        }
    }

    inner class WorkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvTitle: AppCompatTextView = itemView.findViewById(R.id.tvTitleJob)
        private var tvStatusJob: AppCompatTextView = itemView.findViewById(R.id.tvStatusJob)
        fun binData(jobWork: WorkoutModel.JOB_MODEL) {
            jobWork.assignment.let {
                tvTitle.text = it.title
                tvStatusJob.text = "${it.totalExercise} exercises"
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WorkoutModel>() {
            override fun areItemsTheSame(oldItem: WorkoutModel, newItem: WorkoutModel): Boolean {
                val isSameRepoItem = oldItem is WorkoutModel.DAY_NOT_JOB_MODEL
                        && newItem is WorkoutModel.DAY_NOT_JOB_MODEL
                        && oldItem.work.id == newItem.work.id

                val isSameSeparatorItem = oldItem is WorkoutModel.DAY_AND_JOB_MODEL
                        && newItem is WorkoutModel.DAY_AND_JOB_MODEL
                        && oldItem.work.id == newItem.work.id

                return isSameRepoItem || isSameSeparatorItem
            }

            override fun areContentsTheSame(oldItem: WorkoutModel, newItem: WorkoutModel): Boolean =
                oldItem == newItem
        }
    }
}