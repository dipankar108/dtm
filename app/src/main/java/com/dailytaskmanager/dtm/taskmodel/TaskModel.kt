package com.dailytaskmanager.dtm.taskmodel

data class TaskModel(
    val title: String = "No Title",
    val desc: String = "Description",
    val taskStartTime: Long = 0,
    val taskFinishedTime: Long = 0,
    val taskReminder: Boolean = false,
    val taskAlarm: Boolean = false,
    val taskReminderId: String = "",
    val taskAlarmId: String = "",
    val taskStartDate: String = "12/12/12",
    val taskFinishedDate: String = "12/12/12",
    val taskIsComplete: Boolean = false
)
