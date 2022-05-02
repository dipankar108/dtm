package com.dailytaskmanager.dtm.taskmodel

object TaskDemuData {
   private val taskList = arrayListOf<TaskModel>()

    init {
        if (taskList.isEmpty()) {
            addTask()
        }
    }

    fun getTask() = taskList

    fun addTask() {
        taskList.add(
            TaskModel(
                "Do android programming",
                "This task is complete",
                0,
                0,
                false,
                false,
                "No",
                "No",
                "12/12/12",
                "12/12/12/",
                true
            )
        )
        taskList.add(
            TaskModel(
                "Do android programming",
                "This task is incomplete",
                0,
                0,
                false,
                false,
                "No",
                "No",
                "12/12/12",
                "12/12/12/",
                false
            )
        )
    }
}