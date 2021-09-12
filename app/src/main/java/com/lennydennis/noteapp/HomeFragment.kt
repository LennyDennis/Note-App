package com.lennydennis.noteapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lennydennis.noteapp.adapters.NoteAdapter
import com.lennydennis.noteapp.data.DataManager
import com.lennydennis.noteapp.databinding.FragmentHomeBinding
import com.lennydennis.noteapp.models.Note

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var mNoteAdapter: NoteAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddCourse.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_editNoteFragment)
        }

        setUpRecyclerView(DataManager.notes)
    }

    private fun setUpRecyclerView(notes:ArrayList<Note>){
        val noteListAdapter = NoteAdapter(notes)
        binding.rvNotes.layoutManager = LinearLayoutManager(context)
        binding.rvNotes.adapter = noteListAdapter

        noteListAdapter.noteClickListener = object : NoteAdapter.NoteClickListener{
            override fun onNoteClicked(notePosition: Int) {
                val bundle = bundleOf(getString(R.string.note_position) to notePosition)
                findNavController().navigate(R.id.action_homeFragment_to_editNoteFragment, bundle)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}