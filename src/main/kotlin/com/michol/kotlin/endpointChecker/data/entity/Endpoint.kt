package com.michol.kotlin.endpointChecker.data.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Endpoint(
        val url: String = "",
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        private val id: Long = 0
){
    override fun toString(): String {
        return "Endpoint(id=$id, url='$url')"
    }
}