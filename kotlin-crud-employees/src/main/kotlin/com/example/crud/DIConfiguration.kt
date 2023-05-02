package com.example.crud

import com.example.crud.domain.EmployeeService
import com.example.crud.dataAccess.dao.EmployeeDao
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DIConfiguration {

    @Bean
    fun employeeService(employeeDao: EmployeeDao) : EmployeeService = EmployeeService(employeeDao)

}