package com.lennydennis.noteapp

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lennydennis.noteapp.data.DataManager
import com.lennydennis.noteapp.models.Course
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreateNewNoteTest{

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun createNewNote(){
        val noteTitle = "Test Note"
        val noteText = "This is a test note"
        val course = DataManager.courses["Course 1"]!!

        onView(withId(R.id.fab_add_course)).perform(click())

        onView(withId(R.id.course_spinner)).perform(click())
        onData(allOf(instanceOf(Course::class.java), equalTo(course))).perform(click())

        onView(withId(R.id.et_note_title)).perform(typeText(noteTitle))
        onView(withId(R.id.et_note_text)).perform(typeText(noteText), closeSoftKeyboard())

        pressBack()

        val note = DataManager.notes.last()
        assertEquals(course,note.course)
        assertEquals(noteTitle,note.title)
        assertEquals(noteText,note.text)

    }

}
