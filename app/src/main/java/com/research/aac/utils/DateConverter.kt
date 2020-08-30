package com.research.aac.utils

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate

class DateConverter {

    @TypeConverter
    fun toLocalDate(epochDay: Long): LocalDate? {
        return LocalDate.ofEpochDay(epochDay)
    }

    @TypeConverter
    fun toLong(date: LocalDate): Long {
        return date.toEpochDay()
    }
}