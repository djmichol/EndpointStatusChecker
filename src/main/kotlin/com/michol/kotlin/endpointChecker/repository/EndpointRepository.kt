package com.michol.kotlin.endpointChecker.repository

import com.michol.kotlin.endpointChecker.data.entity.Endpoint
import org.springframework.data.repository.CrudRepository

interface EndpointRepository : CrudRepository<Endpoint, Long>