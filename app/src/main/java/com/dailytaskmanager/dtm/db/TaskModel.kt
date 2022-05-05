package com.dailytaskmanager.dtm.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskManager")
data class TaskModel(
    @PrimaryKey
    var id: Int = 1,
    var title: String = "No Title",
    var desc: String = "Description",
    var taskStartTimeMIlls: Long = 0,
    var taskTotalTimeMIlls: Long = 0,
    var taskStartReminderId: Int = id,
    var taskStartAlarmId: Int = taskStartReminderId + 1,
    var taskEndReminderId: Int = taskStartAlarmId + 1,
    var taskEndAlarmId: Int = taskEndReminderId + 1,
    var istaskReminder: Boolean = false,
    var istaskAlarm: Boolean = false,
    var taskStartDate: String = "12/12/12",
    var istaskIsComplete: Boolean = false,
    var taskStartTime: String = "12:12 AM",
)