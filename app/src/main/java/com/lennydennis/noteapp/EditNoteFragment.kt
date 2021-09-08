package com.lennydennis.noteapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.lennydennis.noteapp.data.DataManager
import com.lennydennis.noteapp.databinding.FragmentEditNoteBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class EditNoteFragment : Fragment() {

    private var _binding: FragmentEditNoteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataManager = DataManager();

        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            dataManager.courses.values.toList(),
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