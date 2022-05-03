package com.dailytaskmanager.dtm.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskManager")
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1,
    var title: String = "No Title",
    var desc: String = "Description",
    var taskStartTime: Long = 0,
    var taskFinishedTime: Long = 0,
    var taskReminder: Boolean = false,
    var taskAlarm: Boolean = false,
    var taskReminderId: String = "",
    var taskAlarmId: String = "",
    var taskStartDate: String = "12/12/12",
    var taskFinishedDate: String = "12/12/12",
    var taskIsComplete: Boolean = false,

    )