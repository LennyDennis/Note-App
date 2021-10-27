package com.lennydennis.noteapp.data

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DataManagerTest {

    @Before
    fun setUp() {
        DataManager.notes.clear()
        DataManager.createCourses()
    }

    @Test
    fun addNote() {
        val course = DataManager.courses["Course 1"]!!
        val noteTitle = "Test Note"
        val noteText = "This is a test note"
        val index = DataManager.addNote(course, noteTitle, noteText)
        val note = DataManager.notes[index]
        assertEquals(course, note.course)
        assertEquals(noteTitle, note.title)
        assertEquals(noteText, note.text)
    }

    @Test
    fun findSimilarNotes() {
        val course = DataManager.courses["Course 1"]!!
        val noteTitle = "Test Note"
        val noteText = "This is a test note"
        val noteTextOne = "This is test note 1"

        val index = DataManager.addNote(course, noteTitle, noteText)
        val indexOne = DataManager.addNote(course, noteTitle, noteTextOne)

        val note = DataManager.findNote(course, noteTitle, noteText)
        val noteIndex = DataManager.notes.indexOf(note)
        assertEquals(index, noteIndex)

        val noteOne = DataManager.findNote(course, noteTitle, noteTextOne)
        val noteOneIndex = DataManager.notes.indexOf(noteOne)
        assertEquals(indexOne, noteOneIndex)
    }


}