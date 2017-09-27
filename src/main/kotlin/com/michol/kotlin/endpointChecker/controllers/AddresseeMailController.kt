package com.michol.kotlin.endpointChecker.controllers

import com.michol.kotlin.endpointChecker.data.entity.AddresseeMail
import com.michol.kotlin.endpointChecker.repository.AddresseeMailRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/addresseeMail")
class AddresseeMailController(val addresseeMailRepository: AddresseeMailRepository) {

    @GetMapping
    fun findAll(): Iterable<AddresseeMail> = addresseeMailRepository.findAll()

    @PostMapping
    fun addAddressee(@RequestBody addressee: AddresseeMail): AddresseeMail = addresseeMailRepository.save(addressee)

}