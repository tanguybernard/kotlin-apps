package com.example.crud.presentation

import com.example.crud.dataAccess.entity.EmployeeEntity
import com.example.crud.domain.Employee
import com.example.crud.domain.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController()
@RequestMapping("/employees")
class EmployeeController(val service: EmployeeService) {


    @PostMapping
    fun createEmployee(@RequestBody employee: EmployeeDto): ResponseEntity<Employee> {
        val employee = service.addEmployee(Employee(UUID.randomUUID().toString(),employee.name, employee.address))

        return ResponseEntity.status(HttpStatus.CREATED).body(employee);

    }

    @GetMapping
    fun findAllEmployee(): Iterable<EmployeeEntity?>? {
        return service.findAllEmployee()
    }
}