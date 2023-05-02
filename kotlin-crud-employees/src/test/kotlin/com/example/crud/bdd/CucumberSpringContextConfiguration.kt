package com.example.crud.bdd

import com.example.crud.KotlinCrudEmployeesApplication
import io.cucumber.java.Before
import io.cucumber.spring.CucumberContextConfiguration
//import io.cucumber.spring.CucumberContextConfiguration
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.ContextConfiguration


@CucumberContextConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = [KotlinCrudEmployeesApplication::class], loader = SpringBootContextLoader::class)
class CucumberSpringContextConfiguration {
    /**
     * Need this method so the cucumber will recognize this class as glue and load
     * spring context configuration
     */
    @Before
    fun setUp() {
        LOG.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------")
    }

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(CucumberSpringContextConfiguration::class.java)
    }
}