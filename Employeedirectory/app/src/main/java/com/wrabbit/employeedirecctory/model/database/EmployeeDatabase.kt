package com.wrabbit.employeedirecctory.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wrabbit.employeedirecctory.model.models.Employee
import com.wrabbit.employeedirecctory.util.ROOM_DB_NAME

@Database(entities = [Employee::class], version = 7, exportSchema = false)
abstract class EmployeeDatabase:RoomDatabase() {

    abstract fun getEmployeeDao(): EmployeeDAO

    companion object {
        private var INSTANCE: EmployeeDatabase? = null

        fun getDatabase(context: Context) = INSTANCE ?: kotlin.run {
            Room.databaseBuilder(
                context.applicationContext,
                EmployeeDatabase::class.java,
                ROOM_DB_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}