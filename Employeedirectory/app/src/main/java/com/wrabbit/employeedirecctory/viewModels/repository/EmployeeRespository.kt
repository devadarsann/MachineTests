package com.wrabbit.employeedirecctory.viewModels.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import com.wrabbit.employeedirecctory.api.EmployeeEndpoint
import com.wrabbit.employeedirecctory.model.database.EmployeeDAO
import com.wrabbit.employeedirecctory.model.database.EmployeeDatabase
import com.wrabbit.employeedirecctory.model.models.Employee

class EmployeeRepository private constructor(application: Application):BaseRepository(){

    private val employeeDAO: EmployeeDAO = EmployeeDatabase.getDatabase(application).getEmployeeDao()
    private val employeeApi = getInstance().create(EmployeeEndpoint::class.java)

    suspend fun getAllEmployeesLiveData():LiveData<List<Employee>>{
        return employeeDAO.getAllEmployeesLiveData().also {
            getAllEmployeesFromRemote()
        }
    }

    private suspend fun getAllEmployeesFromRemote() {
        try {
            val employeeseList = employeeApi.getEmployeeList()
            employeeseList.forEach {
                insertEmployee(it)
            }
        }
        catch (exception:Throwable){
            exception.printStackTrace()
        }
    }

    private fun insertEmployee(employee:Employee) {
        employeeDAO.insertEmployee(employee)
    }

    suspend fun getEmployeeByIdLiveData(employeeId: Int): LiveData<Employee?> {
        return employeeDAO.getEmployeeByIdLiveData(employeeId)
    }

    // Singleton Pattern for Repository.
    companion object {
        private var INSTANCE: EmployeeRepository? = null

        fun getInstance(application: Application): EmployeeRepository = INSTANCE ?: kotlin.run {
            INSTANCE = EmployeeRepository(application = application)
            INSTANCE!!
        }
    }
}