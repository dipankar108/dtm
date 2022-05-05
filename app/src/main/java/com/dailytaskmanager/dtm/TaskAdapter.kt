package com.dailytaskmanager.dtm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dailytaskmanager.dtm.db.TaskModel

/** Task Indicator
 * 0 Means Task Finished
 * 1 Means Task Incomplete
 * 2 Means Task Running
 * 3 Means Task Upcoming
 **/
class TaskAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var taskList = listOf<TaskModel>()
    private var viewType = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            0 -> {
                FinishedTask(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.finiahedtask, parent, false)
                )
            }
            1 -> {
                IncompleteTask(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.incompletetask, parent, false)
                )
            }
            2 -> {
                RunningTask(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.runningtask, parent, false)
                )
            }
//            3 -> {
//                UpcomingTask(
//                    LayoutInflater.from(parent.context)
//                        .inflate(R.layout.finiahedtask, parent, false)
//                )
//            }
            else -> {
                UpcomingTask(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.upcomingtask, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount() = taskList.size

    class FinishedTask(finishedTask: View) : RecyclerView.ViewHolder(finishedTask) {

    }

    class UpcomingTask(upcomingTask: View) : RecyclerView.ViewHolder(upcomingTask) {

    }

    class IncompleteTask(incompleteTask: View) : RecyclerView.ViewHolder(incompleteTask) {

    }

    class RunningTask(runningTask: View) : RecyclerView.ViewHolder(runningTask) {

    }

    fun setTaskList(taskList: List<TaskModel>) {
        this.taskList = taskList
    }

    override fun getItemViewType(position: Int): Int {
        val res = taskList[position]
        val currentTime = System.currentTimeMillis()
//        return if (res.istaskIsComplete) {
//            0
//        } else {
//            when {
//                res.istaskFinishedTime >= currentTime -> {
//                    1
//                }
//                res.taskStartTime > currentTime -> {
//                    2
//                }
//                else -> {
//                    3
//                }
//            }
//        }
        return 1
    }
}