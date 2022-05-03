package com.dailytaskmanager.dtm.db

import androidx.lifecycle.LiveData

class Repository(private val taskDao: TaskDao) {
     fun insertTask(taskModel: TaskModel) {
        taskDao.addUser(taskModel)
    }

    fun getAllTask(): LiveData<List<TaskModel>> = taskDao.readAllData()
}