package com.michol.kotlin.endpointChecker.repository

import com.michol.kotlin.endpointChecker.data.entity.AddresseeMail
import org.springframework.data.repository.CrudRepository

interface AddresseeMailRepository : CrudRepository<AddresseeMail, Long>