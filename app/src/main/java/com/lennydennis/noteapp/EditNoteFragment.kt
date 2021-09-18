package com.lennydennis.noteapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.lennydennis.noteapp.data.DataManager
import com.lennydennis.noteapp.databinding.FragmentEditNoteBinding

class EditNoteFragment : Fragment() {

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!
    private var notePosition:Int? = -1

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

        notePosition = arguments?.getInt(getString(R.string.note_position))
        if(notePosition != -1 ){
            notePosition?.let {
                populateNote()
            }
        }
    }

    private fun populateNote() {
        val notes = DataManager.notes
        val note = notes[notePosition!!]
        binding.etNoteTitle.setText(note.title)
        binding.etNoteText.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        binding.courseSpinner.setSelection(coursePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_next ->{
                notePosition?.let{
                    moveToNextNote()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveToNextNote() {
        notePosition = notePosition?.plus(1)
        populateNote()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}