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
            R.layout.workout_item_view -> OnlyJobViewHolder(v)
            else -> throw IllegalStateException("Unknown view")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is DayAndWorkViewHolder -> holder.binData(item as WorkoutModel.DayAndJobModel)
            is DayNotWorkViewHolder -> holder.binData(item as WorkoutModel.DayNotJobModel)
            is OnlyJobViewHolder -> holder.binData(item as WorkoutModel.OnlyJobModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is WorkoutModel.DayAndJobModel -> R.layout.day_and_job_item_view
            is WorkoutModel.DayNotJobModel -> R.layout.day_not_job_item_view
            is WorkoutModel.OnlyJobModel -> R.layout.workout_item_view
            else -> throw IllegalStateException("Unknown view")
        }
    }

    inner class DayAndWorkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvDate: AppCompatTextView = itemView.findViewById(R.id.tvDate)
        private var tvDay: AppCompatTextView = itemView.findViewById(R.id.tvDay)
        private var tvTitle: AppCompatTextView = itemView.findViewById(R.id.tvTitle)
        private var tvJob: AppCompatTextView = itemView.findViewById(R.id.tvJob)
        fun binData(dayAndJob: WorkoutModel.DayAndJobModel) {
            tvDate.text = dayAndJob.dateOfWeek
            tvDay.text = dayAndJob.dayOfWeek
            tvTitle.text = dayAndJob.assignment.title
            tvJob.text = "${dayAndJob.assignment.totalExercise} exercises"
        }
    }

    inner class DayNotWorkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvDate: AppCompatTextView = itemView.findViewById(R.id.tvDate)
        private var tvDay: AppCompatTextView = itemView.findViewById(R.id.tvDay)
        fun binData(dayNotWork: WorkoutModel.DayNotJobModel) {
            tvDate.text = dayNotWork.dateOfWeek
            tvDay.text = dayNotWork.dayOfWeek
        }
    }

    inner class OnlyJobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvTitle: AppCompatTextView = itemView.findViewById(R.id.tvTitleJob)
        private var tvStatusJob: AppCompatTextView = itemView.findViewById(R.id.tvStatusJob)
        fun binData(jobWork: WorkoutModel.OnlyJobModel) {
            tvTitle.text = jobWork.assignment.title
            tvStatusJob.text = "${jobWork.assignment.totalExercise} exercises"
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WorkoutModel>() {
            override fun areItemsTheSame(oldItem: WorkoutModel, newItem: WorkoutModel): Boolean {
                if (oldItem is WorkoutModel.DayAndJobModel && newItem is WorkoutModel.DayAndJobModel) {
                    return oldItem.dateOfWeek == newItem.dateOfWeek && oldItem.assignment.id == newItem.assignment.id
                }
                if (oldItem is WorkoutModel.DayNotJobModel && newItem is WorkoutModel.DayNotJobModel) {
                    return oldItem.dateOfWeek == newItem.dateOfWeek
                }
                if (oldItem is WorkoutModel.OnlyJobModel && newItem is WorkoutModel.OnlyJobModel) {
                    return oldItem.assignment.id == newItem.assignment.id
                }
                return false
            }

            override fun areContentsTheSame(oldItem: WorkoutModel, newItem: WorkoutModel): Boolean =
                oldItem == newItem
        }
    }
}