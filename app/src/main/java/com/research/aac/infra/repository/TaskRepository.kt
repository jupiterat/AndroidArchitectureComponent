package com.research.aac.infra.repository

import androidx.lifecycle.LiveData
import com.research.aac.infra.db.dao.TaskDao
import com.research.aac.infra.entity.Task

class TaskRepository(val taskDao: TaskDao) : Repository<Task> {

    override fun insert(task: Task) {
        taskDao.insert(task)
    }

    override fun getAll(): LiveData<List<Task>> {
        return taskDao.getTasks()
    }

    override fun update(task: Task) {

    }

    override fun delete(task: Task) {
        taskDao.delete(task)
    }
}