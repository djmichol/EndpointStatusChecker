package com.michol.kotlin.endpointChecker.handlers

import com.michol.kotlin.endpointChecker.data.response.EndpointStatusResponse
import com.michol.kotlin.endpointChecker.repository.EndpointRepository
import com.michol.kotlin.endpointChecker.tools.ConnectionTools
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class EndpointPingHandler(private val connectionTools: ConnectionTools, private val repository: EndpointRepository) {

    fun handleSingle(endpointId: Long): EndpointStatusResponse {
        val endpoint = repository.findOne(endpointId)
        return EndpointStatusResponse(HttpStatus.valueOf(connectionTools.getUrlStatus(endpoint.url, 300)), endpoint.url)
    }

    fun handleAll(): Iterable<EndpointStatusResponse>{
        val endpoints = repository.findAll()
        return endpoints.flatMap { listOf(EndpointStatusResponse(HttpStatus.valueOf(connectionTools.getUrlStatus(it.url, 300)), it.url)) }
    }
}