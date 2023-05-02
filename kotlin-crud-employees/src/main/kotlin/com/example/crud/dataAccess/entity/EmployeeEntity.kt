package com.example.crud.dataAccess.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.Hibernate


@Entity
@Table(name = "employee")
data class EmployeeEntity(


    @get:Id
    @Column(name = "id", nullable = false)
    var id: String,


    @get:Column(name="name")
    var name: String,


    @get:Column(name="address")
    var address : String

)