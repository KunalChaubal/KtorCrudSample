package com.helloworld.example.route

import com.helloworld.example.model.Customer
import com.helloworld.example.repository.CustomerRepo
import io.ktor.application.*
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*

fun Route.getCustomer(customerRepo: CustomerRepo) {
    route("/customer") {

        get {
            call.respond(HttpStatusCode.OK, customerRepo.customerList)
        }

        get("/{customerId}") {
            val customerId = call.parameters["customerId"]?.toInt()
            val requestCustomer = customerRepo.customerList.firstOrNull { it.id == customerId }
            if (requestCustomer != null) {
                call.respond(HttpStatusCode.OK, requestCustomer)
            } else {
                call.respond(HttpStatusCode.NotFound, "Record not found")
            }
        }

        post("/create") {
            val customer = call.receive<Customer>()
            customerRepo.customerList.add(customer)
            call.respond(HttpStatusCode.OK, "Record Created")
        }

    }
}