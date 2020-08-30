package com.research.aac.viewmodel

import androidx.lifecycle.ViewModel
import com.research.aac.view.model.Task
import org.threeten.bp.LocalDate

class AddTaskViewModel : ViewModel() {
    val task = Task()

    fun onDateChanged(year: Int, month: Int, day: Int) {
        task.deadline = LocalDate.of(year, month + 1, day)
    }

}