package com.dailytaskmanager.dtm

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dailytaskmanager.dtm.databinding.ActivityMainBinding
import com.dailytaskmanager.dtm.taskfragment.AllTaskFragment
import com.dailytaskmanager.dtm.taskfragment.RunningTaskFragment
import com.dailytaskmanager.dtm.taskfragment.UpcomingTaskFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val actionbar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#17EBDE"))
        actionbar?.setBackgroundDrawable(colorDrawable)
        setContentView(binding.root)
        changeFragment(RunningTaskFragment())
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
    }

    fun changeFragment(mFragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_mainActivity_id, mFragment)
            .commit()
    }
}