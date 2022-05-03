package com.dailytaskmanager.dtm

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dailytaskmanager.dtm.databinding.ActivityMainBinding
import com.dailytaskmanager.dtm.db.TaskManagerViewModel
import com.dailytaskmanager.dtm.taskfragment.AllTaskFragment
import com.dailytaskmanager.dtm.taskfragment.RunningTaskFragment
import com.dailytaskmanager.dtm.taskfragment.UpcomingTaskFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskLiveData: TaskManagerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val actionbar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#17EBDE"))
        actionbar?.setBackgroundDrawable(colorDrawable)
        setContentView(binding.root)
        changeFragment(RunningTaskFragment())
        val viewModelFactory = TaskManagerViewModel(application)
        taskLiveData = ViewModelProvider(this, object :
            ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return viewModelFactory as T
            }
        })[TaskManagerViewModel::class.java]
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.menu_allTask_id -> {
                    changeFragment(AllTaskFragment())
                }
                R.id.menu_running_id -> {
                    changeFragment(RunningTaskFragment())
                }
                R.id.menu_upcomingTask_id -> {
                    changeFragment(UpcomingTaskFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
        binding.fabAddTaskId.setOnClickListener {
            startActivity(Intent(this, CreateTaskActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            })
        }
    }

    fun changeFragment(mFragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_mainActivity_id, mFragment)
            .commit()
    }
}