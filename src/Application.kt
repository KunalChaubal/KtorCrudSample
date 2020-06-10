package com.helloworld.example

import com.helloworld.example.repository.CustomerRepo
import com.helloworld.example.route.getCustomer
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    val server = embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }
        routing {
            helloWorld()
            getCustomer(customerRepo = CustomerRepo())
        }
    }
    server.start(wait = true)
}

fun Route.helloWorld() {
    get("/") {
        call.respondText("Hello World!", ContentType.Text.Plain)
    }
}