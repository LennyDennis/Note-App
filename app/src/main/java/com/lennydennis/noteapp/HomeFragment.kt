package com.lennydennis.noteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lennydennis.noteapp.adapters.CourseAdapter
import com.lennydennis.noteapp.data.DataManager
import com.lennydennis.noteapp.databinding.FragmentHomeBinding
import com.lennydennis.noteapp.models.Course

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var mCourseAdapter: CourseAdapter
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
        val dm = DataManager()
        setUpRecyclerView(dm.courses)
    }

    private fun setUpRecyclerView(courses: HashMap<String, Course>) {
        val courseList = courses.values.toList()
        val noteListAdapter = context?.let { CourseAdapter(courseList, it) }
        binding.rvNotes.layoutManager = LinearLayoutManager(context)
        binding.rvNotes.adapter = noteListAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}