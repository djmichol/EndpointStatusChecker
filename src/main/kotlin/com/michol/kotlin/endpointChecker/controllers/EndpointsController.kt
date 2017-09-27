package com.michol.kotlin.endpointChecker.controllers

import com.michol.kotlin.endpointChecker.data.entity.Endpoint
import com.michol.kotlin.endpointChecker.handlers.EndpointPingHandler
import com.michol.kotlin.endpointChecker.repository.EndpointRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/endpoint")
class EndpointsController (private val repository: EndpointRepository, private val endpointPingHandler: EndpointPingHandler){

    @GetMapping
    fun findAll(): Iterable<Endpoint> = repository.findAll()

    @PostMapping
    fun addEndpoint(@RequestBody endpoint: Endpoint): Endpoint = repository.save(endpoint)

    @GetMapping("/{endpointId}")
    fun pingEndpoint(@PathVariable("endpointId") endpointId: Long) = endpointPingHandler.handleSingle(endpointId)

    @GetMapping("/checkAll")
    fun pingAllEndpoints() = endpointPingHandler.handleAll()

}