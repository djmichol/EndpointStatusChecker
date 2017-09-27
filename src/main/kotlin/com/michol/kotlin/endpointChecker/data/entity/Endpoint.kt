package com.michol.kotlin.endpointChecker.data.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Endpoint(
        val url: String = "",
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
         val id: Long = 0
)