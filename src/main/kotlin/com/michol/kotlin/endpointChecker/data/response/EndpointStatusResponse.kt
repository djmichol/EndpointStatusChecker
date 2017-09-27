package com.michol.kotlin.endpointChecker.data.response

import org.springframework.http.HttpStatus

data class EndpointStatusResponse(
        val status: HttpStatus,
        val url: String = ""
)