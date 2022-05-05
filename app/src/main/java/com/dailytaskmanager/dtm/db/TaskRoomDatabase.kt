package com.dailytaskmanager.dtm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TaskModel::class], version = 2, exportSchema = false)
abstract class TaskRoomDatabase : RoomDatabase() {
    abstract fun taskModelDao(): TaskDao

    companion object {
        private var INSTANCE: TaskRoomDatabase? = null
        fun getDataBaseInstance(context: Context): TaskRoomDatabase {
            var tempInstance = INSTANCE
            tempInstance?.let {
                return it
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskRoomDatabase::class.java,
                    "user_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}