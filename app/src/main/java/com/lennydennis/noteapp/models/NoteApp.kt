package com.lennydennis.noteapp.models

data class Course(var courseId:String, var title:String) {
    override fun toString(): String {
        return title
    }
}

class Note(var course:Course, var title:String, var text:String)

