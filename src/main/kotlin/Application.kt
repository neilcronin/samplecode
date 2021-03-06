package com.samplecode

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {

    embeddedServer(Netty, port = 8666) {
        install(CallLogging)
        install(ContentNegotiation){
            jackson {}
        }
        routing {
            get("/number-of-business-hours", ) {
                val calculator = BusinessHoursCalculator(Business())
                val start = DateTimeFormat.fromIsoString(call.request.queryParameters["start"]!!)
                val end = DateTimeFormat.fromIsoString(call.request.queryParameters["end"]!!)
                val numberOfHours = calculator.numberOfHoursBetweenDateTimes(start, end)
                call.respond(BusinessHoursResponse(numberOfHours))
            }
            get("/age-validator", ) {
                val validator = AgeValidator(21)
                val dob = call.request.queryParameters["dob"]!!
                val isOfAge = validator.isOfAge(dob)
                call.respond(AgeValidatorResponse(isOfAge))
            }
        }
    }.start(wait = true)
}

data class BusinessHoursResponse(val numberOfHours: Int)

data class AgeValidatorResponse(val isOfAge: Boolean)
