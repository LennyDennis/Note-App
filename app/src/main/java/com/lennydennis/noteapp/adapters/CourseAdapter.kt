package com.lennydennis.noteapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lennydennis.noteapp.databinding.ItemCourseLayoutBinding
import com.lennydennis.noteapp.models.Course

class CourseAdapter(
    private val courseList: List<Course>,
    val context: Context
):RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val courseBinding = ItemCourseLayoutBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return CourseViewHolder(courseBinding);
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val note = courseList[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int = courseList.size

    class CourseViewHolder(noteBinding: ItemCourseLayoutBinding) :
        RecyclerView.ViewHolder(noteBinding.root) {
        private val courseId: TextView = noteBinding.tvCourseId
        private val courseTitle: TextView = noteBinding.tvCourseTitle
        fun bind(course: Course) {
            this.courseId.text = course.courseId
            this.courseTitle.text = course.title
        }
    }

}