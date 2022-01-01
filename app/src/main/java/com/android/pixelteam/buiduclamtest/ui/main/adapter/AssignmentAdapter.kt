package com.android.pixelteam.buiduclamtest.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.pixelteam.buiduclamtest.R
import com.android.pixelteam.buiduclamtest.model.entity.Assignment

class AssignmentAdapter :
    ListAdapter<Assignment, AssignmentAdapter.AssignmentViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Assignment>() {
            override fun areItemsTheSame(oldItem: Assignment, newItem: Assignment): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Assignment, newItem: Assignment): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.assignment_item_view, parent, false)
        return AssignmentViewHolder(v)
    }

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        holder.binData(getItem(position))
    }

    inner class AssignmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvName: AppCompatTextView = itemView.findViewById(R.id.tvName)
        private var tvCount: AppCompatTextView = itemView.findViewById(R.id.tvCountWork)
        private var ivSelect: AppCompatTextView = itemView.findViewById(R.id.ivSelect)
        fun binData(assignment: Assignment) {
            tvName.text = assignment.title
            tvCount.text = "${assignment.totalExercise} exercises"
        }
    }
}