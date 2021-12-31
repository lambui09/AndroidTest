package com.android.pixelteam.buiduclamtest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pixelteam.buiduclamtest.R
import com.android.pixelteam.buiduclamtest.model.entity.Work

class WorkoutAdapter : ListAdapter<Work, WorkoutAdapter.WorkoutViewHolder>(DIFF_CALLBACK) {
    private val viewPool = RecyclerView.RecycledViewPool()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.workout_item_view, parent, false)
        return WorkoutViewHolder(v)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        holder.binData(getItem(position))
    }

    inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvDate: AppCompatTextView = itemView.findViewById(R.id.tvDate)
        private var tvDay: AppCompatTextView = itemView.findViewById(R.id.tvDay)
        private var rvAssignment: RecyclerView = itemView.findViewById(R.id.rvAssignment)
        fun binData(work: Work) {
            work.date?.let {
                tvDate.text = it.toString()
                tvDay.text = "23"
                rvAssignment.apply {
                    adapter = AssignmentAdapter()
                    setRecycledViewPool(viewPool)
                    AssignmentAdapter().submitList(work.list)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Work>() {
            override fun areItemsTheSame(oldItem: Work, newItem: Work): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Work, newItem: Work): Boolean =
                oldItem == newItem
        }
    }
}