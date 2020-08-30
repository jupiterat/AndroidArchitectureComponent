package com.research.aac.infra.db.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.research.aac.infra.entity.Task
import com.research.aac.utils.DateConverter
import org.threeten.bp.LocalDate

@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class DemoDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: DemoDatabase? = null

        fun getInstance(context: Context): DemoDatabase {
            val temp = INSTANCE
            if (temp != null) {
                return temp
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DemoDatabase::class.java,
                    "demo_database"
                )
                    .allowMainThreadQueries()
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                return instance
            }

        }
    }

    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

        }

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }

        fun populateDatabase(taskDao: TaskDao) {
            for (i in 1..5) {
                val task = Task(title = "Task$i", deadline = LocalDate.now())
                taskDao.insert(task)
            }

        }
    }
}