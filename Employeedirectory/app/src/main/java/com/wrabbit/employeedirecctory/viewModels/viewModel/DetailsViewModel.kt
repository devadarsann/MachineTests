package com.wrabbit.employeedirecctory.viewModels.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.wrabbit.employeedirecctory.model.models.Employee
import com.wrabbit.employeedirecctory.viewModels.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers

class DetailsViewModel(application: Application):AndroidViewModel(application) {
    val employeeRepository = EmployeeRepository.getInstance(application)

    fun employeeLiveData(id:Int):LiveData<Employee?> = liveData(Dispatchers.IO) {
        emitSource(employeeRepository.getEmployeeByIdLiveData(id))
    }
}