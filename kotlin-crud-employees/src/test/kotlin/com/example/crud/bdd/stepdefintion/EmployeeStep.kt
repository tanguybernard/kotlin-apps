package com.example.crud.bdd.stepdefintion

import com.example.crud.dataAccess.dao.EmployeeDao
import com.example.crud.dataAccess.entity.EmployeeEntity
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.junit.platform.engine.Cucumber
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ValidatableResponse
import net.minidev.json.JSONObject
import org.assertj.core.api.Assertions.assertThat
import java.util.*


@Cucumber
class EmployeeStep(val employeeDao: EmployeeDao) {
    private var validatableResponse: ValidatableResponse? = null
    private val endpoint = "http://localhost:8080/employees"


    @Given("Employees presents")
    fun employees_presents(table: DataTable) {

        employeeDao.deleteAll()

        val rows: List<Map<String, String>> = table.asMaps(String::class.java, String::class.java)


        for (columns in rows) {

            val name = columns.get("name")
            val address = columns.get("address")
            if (name != null && address != null) {
                employeeDao.save(EmployeeEntity(
                    UUID.randomUUID().toString(),
                    name, address
                ))
            }
        }

    }

    @Given("There is no employees")
    fun there_is_no_employees() {

        employeeDao.deleteAll()
        assertThat(employeeDao.findAll()).hasSize(0)
    }



    @When("I create employees {string} with {string}")
    fun iCreateEmployeesWith(name: String, address: String) {
        val request = RestAssured.given()
        // JSONObject is a class that represents a Simple JSON.
        // We can add Key - Value pairs using the put method
        // JSONObject is a class that represents a Simple JSON.
        // We can add Key - Value pairs using the put method
        val requestParams = JSONObject()
        requestParams.put("name", name)
        requestParams.put("address", address)
        // Add a header stating the Request body is a JSON
        // Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json") // Add the Json to the body of the request

        request.body(requestParams.toJSONString()) // Post the request and check the response

        validatableResponse  = request.post("/employees").then()

    }

    @When("I send a request to get employees")
    fun i_send_a_request_to_the_url_to_get_hello() {
        validatableResponse = RestAssured.given().contentType(ContentType.JSON).`when`().get(endpoint).then()
    }

    @Then("the response will return status {int}")
    fun the_response_will_return_status(statusCode: Int?) {
        validatableResponse!!.assertThat().statusCode(statusCode!!)
    }


    @And("creation employee body contains {string} and {string}")
    fun bodyContains(name: String, address: String) {
        assertThat(validatableResponse!!.extract().response().jsonPath().getString("name")).isEqualTo(name)
        assertThat(validatableResponse!!.extract().response().jsonPath().getString("address")).isEqualTo(address)
    }



}