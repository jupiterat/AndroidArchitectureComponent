package com.research.aac.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.research.aac.infra.db.dao.DemoDatabase
import com.research.aac.infra.entity.Task
import com.research.aac.infra.repository.TaskRepository

class TaskListViewModel(application: Application) :
    AndroidViewModel(application) {
    private val repository: TaskRepository

    val tasks: LiveData<List<Task>> by lazy {
        loadTasks()
    }

    init {
        Log.i("TaskListViewModel", "TaskListViewModel created!")
        val dao = DemoDatabase.getInstance(application).taskDao()
        repository = TaskRepository(dao)
    }

    private fun loadTasks(): LiveData<List<Task>> {
        return repository.getAll()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TaskListViewModel", "TaskListViewModel cleared!")

    }

    fun addTask(data: com.research.aac.view.model.Task) {
        repository.insert(
            Task(
                title = data.title,
                deadline = data.deadline
            )
        )
    }
}