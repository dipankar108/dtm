package com.dailytaskmanager.dtm.taskfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dailytaskmanager.dtm.TaskAdapter
import com.dailytaskmanager.dtm.databinding.FragmentAllTaskBinding
import com.dailytaskmanager.dtm.db.TaskManagerViewModel

class AllTaskFragment : Fragment() {
    private lateinit var _binding: FragmentAllTaskBinding
    private lateinit var taskLiveData: TaskManagerViewModel
    private val binding get() = _binding
    private lateinit var taskAdapter: TaskAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        taskLiveData = ViewModelProvider(this)[TaskManagerViewModel::class.java]
        binding.rvAllTaskViewId.layoutManager = LinearLayoutManager(context)
        taskAdapter = TaskAdapter()
        binding.rvAllTaskViewId.adapter = taskAdapter
        taskLiveData.getAllTask().observe(viewLifecycleOwner) {
            taskAdapter.setTaskList(it)
            taskAdapter.notifyDataSetChanged()
        }
    }
}