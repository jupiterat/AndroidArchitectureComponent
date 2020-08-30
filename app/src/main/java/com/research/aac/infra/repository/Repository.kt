package com.research.aac.infra.repository

import androidx.lifecycle.LiveData
import com.research.aac.view.model.Task

interface Repository<T> {
    fun insert(t: T)
    fun getAll(): LiveData<List<T>>
    fun update(t: T)
    fun delete(t: T)

}