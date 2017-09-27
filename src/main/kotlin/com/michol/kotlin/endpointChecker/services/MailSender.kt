package com.michol.kotlin.endpointChecker.services

import com.michol.kotlin.endpointChecker.repository.AddresseeMailRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
class MailSender(private val addresseeMailRepository: AddresseeMailRepository) : IMessageSender {

    private val logger = LoggerFactory.getLogger(MailSender::class.java)

    override fun sendMessage(message: String) {
        addresseeMailRepository.findAll().forEach(Consumer { addressee -> logger.info("Send message "+addressee.mailAddress)})
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}