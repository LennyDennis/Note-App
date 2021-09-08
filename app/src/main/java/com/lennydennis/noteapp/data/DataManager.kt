package com.lennydennis.noteapp.data

import com.lennydennis.noteapp.models.Course
import com.lennydennis.noteapp.models.Note

class DataManager {

    var courses = HashMap<String, Course>()
    var note = ArrayList<Note>()

    init {
        createCourses()
    }

    private fun createCourses() {
        var course = Course("Course 1","Course 1 Title 1")
        courses[course.courseId] = course
        course = Course("Course 2","Course 2 Title 1")
        courses[course.courseId] = course
    }
}