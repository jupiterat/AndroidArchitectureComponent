package com.research.aac.infra.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.research.aac.infra.entity.Task

@Dao
interface TaskDao {
    @Query("SELECT * from task_table ORDER BY deadline DESC")
    fun getTasks() : LiveData<List<Task>>

    @Insert
    fun insert(task : Task)

    @Delete
    fun delete(task : Task)
}