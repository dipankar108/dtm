package com.dailytaskmanager.dtm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: TaskModel)

    @Query("SELECT * FROM taskManager")
    fun readAllData(): LiveData<List<TaskModel>>

}