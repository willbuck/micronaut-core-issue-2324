package example.micronaut

import io.micronaut.http.HttpStatus

data class ValidationResponseValid(val code: HttpStatus = HttpStatus.ACCEPTED, val message: String = "Success")
