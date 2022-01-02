package com.android.pixelteam.buiduclamtest.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pixelteam.buiduclamtest.R
import com.android.pixelteam.buiduclamtest.extensions.gone
import com.android.pixelteam.buiduclamtest.extensions.show
import com.android.pixelteam.buiduclamtest.utils.enum.Status

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
        private var container: ConstraintLayout = itemView.findViewById(R.id.containerJob)
        private var ivSelect: AppCompatImageView = itemView.findViewById(R.id.ivSelect)
        fun binData(dayAndJob: WorkoutModel.DayAndJobModel) {
            tvDate.text = dayAndJob.dateOfWeek
            tvDay.text = dayAndJob.dayOfWeek
            tvTitle.text = dayAndJob.assignment.title
            tvJob.text = "${dayAndJob.assignment.totalExercise} exercises"
            val status = dayAndJob.assignment.status
            container.isActivated = status == Status.COMPLETE.value
            ivSelect.show(status == Status.COMPLETE.value)
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
        private var container: LinearLayout = itemView.findViewById(R.id.container)
        private var ivSelect: AppCompatImageView = itemView.findViewById(R.id.ivSelect)
        fun binData(jobWork: WorkoutModel.OnlyJobModel) {
            val status = jobWork.assignment.status
            tvTitle.text = jobWork.assignment.title
            tvStatusJob.text = "${jobWork.assignment.totalExercise} exercises"
            container.isActivated = status == Status.COMPLETE.value
            ivSelect.show(status == Status.COMPLETE.value)
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