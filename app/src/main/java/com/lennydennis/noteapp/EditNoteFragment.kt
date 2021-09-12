package com.lennydennis.noteapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.lennydennis.noteapp.data.DataManager
import com.lennydennis.noteapp.databinding.FragmentEditNoteBinding

class EditNoteFragment : Fragment() {

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        val notePosition = arguments?.getInt(getString(R.string.note_position))
        notePosition?.let { populateNote(it) }
        return binding.root

    }

    private fun populateNote(notePosition: Int) {
        val notes = DataManager.notes
        val note = notes[notePosition]
        binding.etNoteTitle.setText(note.title)
        binding.etNoteText.setText(note.text)
        val coursePosition = DataManager.courses.values.indexOf(note.course)
        binding.courseSpinner.setSelection(coursePosition)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var courseTitle = ArrayList<String>();
        for (course in DataManager.courses.values) {
            courseTitle.add(course.title);
        }

        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            courseTitle,
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            binding.courseSpinner.adapter = arrayAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}