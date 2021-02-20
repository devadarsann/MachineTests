package com.wrabbit.employeedirecctory.api

import com.wrabbit.employeedirecctory.model.models.Employee
import retrofit2.http.GET

interface EmployeeEndpoint {
    @GET("v2/5d565297300000680030a986")
    suspend fun getEmployeeList():List<Employee>
}