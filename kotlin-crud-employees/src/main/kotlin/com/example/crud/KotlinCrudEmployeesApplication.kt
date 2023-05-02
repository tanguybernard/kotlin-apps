package com.example.crud

import com.example.crud.dataAccess.entity.EmployeeEntity
import com.example.crud.dataAccess.dao.EmployeeDao
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class KotlinCrudEmployeesApplication {

    // Initialize some sample data
    @Bean
    fun init(repository: EmployeeDao): ApplicationRunner {
        return ApplicationRunner { _: ApplicationArguments? ->
            var paperAirplane = EmployeeEntity("123", "Bi-Fold", "zz");
            repository.save(paperAirplane);
            paperAirplane = EmployeeEntity("124", "Tri-Fold", "ee");
            repository.save(paperAirplane);
            paperAirplane = EmployeeEntity("125", "Pink-Fold", "dddd");
            repository.save(paperAirplane);
        }
    }

}

fun main(args: Array<String>) {
    runApplication<KotlinCrudEmployeesApplication>(*args)
}
