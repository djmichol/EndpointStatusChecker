package com.michol.kotlin.endpointChecker.data.entity

import javax.persistence.*

@Entity
data class AddresseeMail(
        @Column(unique = true)
        val mailAddress: String = "",
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0
)