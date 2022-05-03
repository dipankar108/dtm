package com.dailytaskmanager.dtm.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskManagerViewModel(context: Application) :
    AndroidViewModel(context) {
    private var repository: Repository
    private var allTaskList = MutableLiveData<List<TaskModel>>()

    init {
        val getDatabase = TaskRoomDatabase.getDataBaseInstance(context).taskModelDao()
        repository = Repository(getDatabase)
    }

    fun insertTask(taskModel: TaskModel) {
        GlobalScope.launch { repository.insertTask(taskModel) }
    }

    fun getAllTask(): LiveData<List<TaskModel>> =
        repository.getAllTask()
}

