package com.lennydennis.noteapp.models

data class Course(var courseId:String, var title:String) {
}

class Note(var course:Course, var title:String, var text:String)

