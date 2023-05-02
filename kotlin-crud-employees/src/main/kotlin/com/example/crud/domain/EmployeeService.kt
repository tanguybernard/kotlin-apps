package com.example.crud.domain

import com.example.crud.dataAccess.entity.EmployeeEntity
import com.example.crud.dataAccess.dao.EmployeeDao


class EmployeeService(val employeeDao: EmployeeDao) {

    fun addEmployee(emp: Employee): Employee {

        // EmployeeDao does not know about the Employee domain object
        // so EmployeeService needs to convert Employee to EmployeeEntity before calling EmployeeDao.save(..).
        val empEntity = EmployeeEntity(emp.id, emp.name, emp.address)


        val empSaved = employeeDao.save(empEntity)
        println("Employee saved::$empSaved")
        return Employee(empSaved.id, empSaved.name, empSaved.address)
    }

    fun findEmployeeById(empId: String): EmployeeEntity? {
        val emp: EmployeeEntity? = employeeDao.findById(empId).orElseThrow()
        println("Employee found::$emp")
        return emp
    }

    fun findAllEmployee(): Iterable<EmployeeEntity?> {
        return employeeDao.findAll()
    }

    fun deleteEmployeeById(emp: EmployeeEntity) {
        employeeDao.delete(emp)
        println("Employee deleted::$emp")
    }

}