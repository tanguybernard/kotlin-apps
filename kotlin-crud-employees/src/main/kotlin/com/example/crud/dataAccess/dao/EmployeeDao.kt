package com.example.crud.dataAccess.dao

import com.example.crud.dataAccess.entity.EmployeeEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeDao : CrudRepository<EmployeeEntity, String>