package com.lennydennis.noteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val noteList = notes
        val noteListAdapter = context?.let { NoteAdapter(noteList, it) }
        binding.rvNotes.layoutManager = LinearLayoutManager(context)
        binding.rvNotes.adapter = noteListAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}