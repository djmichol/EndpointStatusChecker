package com.michol.kotlin.endpointChecker.components

interface IMessageSender {

    fun sendMessage(message: String, url: String)

}