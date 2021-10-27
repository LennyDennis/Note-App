package com.lennydennis.noteapp.data

import com.lennydennis.noteapp.models.Course
import com.lennydennis.noteapp.models.Note

object DataManager {
    var courses = HashMap<String, Course>()
    var notes = ArrayList<Note>()

    init {
        createCourses()
    }

    fun addNote(course: Course, noteTitle: String, noteText: String): Int {
        notes.add(Note(course, noteTitle, noteText))
        return notes.lastIndex
    }

    fun createCourses() {
        var course = Course("Course 1", "Android")
        courses[course.courseId] = course
        course = Course("Course 2", "Web")
        courses[course.courseId] = course
        createNotes()
    }

    fun createNotes() {
        for ((index, value) in courses.values.withIndex()) {
            var note = Note(value, "Note ${notes.size + 1}", "This is the first note A$index")
            notes.add(note)
            note = Note(value, "Note ${notes.size + 1}", "This is the first note B$index")
            notes.add(note)
            note = Note(value, "Note ${notes.size + 1}", "This is the first note C$index")
            notes.add(note)
        }
    }


    fun findNote(course: Course, noteTitle: String, noteText: String): Note? {
        for (note in notes) {
            if (course == note.course &&
                noteTitle == note.title &&
                noteText == note.text
            )
                return note
        }
        return null
    }
}