package com.michol.kotlin.endpointChecker.components

import com.michol.kotlin.endpointChecker.repository.AddresseeMailRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
class MailSender(private val addresseeMailRepository: AddresseeMailRepository) : IMessageSender {

    private val logger = LoggerFactory.getLogger(MailSender::class.java)

    override fun sendMessage(message: String) {
        addresseeMailRepository.findAll().forEach(Consumer { addressee -> logger.info("Send message: "+ message + " to "+addressee.mailAddress)})
        TODO("Add send mail implementation") //To change body of created functions use File | Settings | File Templates.
    }
}