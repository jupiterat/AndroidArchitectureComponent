package com.research.aac.infra.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.research.aac.utils.DateConverter
import org.threeten.bp.LocalDate

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    @ColumnInfo(name = "task") val title: String,

    @ColumnInfo(name = "deadline") val deadline: LocalDate = LocalDate.now()
)