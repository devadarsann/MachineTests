package com.wrabbit.employeedirecctory.viewModels.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.wrabbit.employeedirecctory.model.models.Employee
import com.wrabbit.employeedirecctory.viewModels.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers

class EmployeeViewModel(application: Application):AndroidViewModel(application) {

    private val employeeRepository = EmployeeRepository.getInstance(application)

    val employeeListLiveData:LiveData<List<Employee>> = liveData(Dispatchers.IO){
        emitSource(employeeRepository.getAllEmployeesLiveData())
    }
}