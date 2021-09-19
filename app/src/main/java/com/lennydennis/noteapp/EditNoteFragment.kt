package com.lennydennis.noteapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.lennydennis.noteapp.data.DataManager
import com.lennydennis.noteapp.databinding.FragmentEditNoteBinding
import com.lennydennis.noteapp.models.Course

class EditNoteFragment : Fragment() {

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!
    private var notePosition: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val courses = ArrayList<Course>();
        for (course in DataManager.courses.values) {
            courses.add(course);
        }

        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            courses,
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            binding.courseSpinner.adapter = arrayAdapter
        }

        notePosition = arguments?.getInt(getString(R.string.note_position))
        if(notePosition != null){
            populateNote(notePosition!!)
        }else{
            notePosition = DataManager.notes.lastIndex
        }
    }

    override fun onPause() {
        saveNote()
        super.onPause()
    }

    private fun saveNote() {
        notePosition?.let{
            val note = DataManager.notes[it]
            note.title = binding.etNoteTitle.text.toString()
            note.text = binding.etNoteText.text.toString()
            note.course = binding.courseSpinner.selectedItem as Course
        }
    }

    private fun populateNote(notePosition: Int) {
        val notes = DataManager.notes
        val note = notes[notePosition]
        binding.etNoteTitle.setText(note.title)
        binding.etNoteText.setText(note.text)
        val coursePosition = DataManager.courses.values.indexOf(note.course)
        binding.courseSpinner.setSelection(coursePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_next -> {
                notePosition?.let {
                    notePosition = it + 1
                    moveNote(notePosition!!)
                }
                true
            }
            R.id.action_previous -> {
                notePosition?.let {
                    notePosition = it - 1
                    moveNote(notePosition!!)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNote(position: Int) {
        populateNote(position)
        activity?.invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        notePosition?.let {
            if(it >= DataManager.notes.lastIndex){
                val nextMenuItem = menu.findItem(R.id.action_next)
                nextMenuItem.isVisible = false
            }
            if(it <= 0){
                val previousMenuItem = menu.findItem(R.id.action_previous)
                previousMenuItem.isVisible = false
            }
        }
        super.onPrepareOptionsMenu(menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}