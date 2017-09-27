package com.michol.kotlin.endpointChecker.components

import com.michol.kotlin.endpointChecker.repository.AddresseeMailRepository
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component
import java.util.function.Consumer


@Component
class MailSender(
        private val addresseeMailRepository: AddresseeMailRepository,
        private val mailSender: JavaMailSender,
        private val environment: Environment
) : IMessageSender {

    private val logger = LoggerFactory.getLogger(MailSender::class.java)

    override fun sendMessage(message: String, url: String) {
        addresseeMailRepository.findAll().forEach(Consumer {
            addressee ->
            run {
                logger.info("Send message: " + message + " to " + addressee.mailAddress)
                sendMail(addressee.mailAddress, message, url)
            }
        })
    }

    fun sendMail(to:String, text: String, url: String){
        val message = SimpleMailMessage()
        message.setTo(to)
        message.from = environment.getProperty("spring.mail.username")
        message.subject = "Endpoint problem $url"
        message.text = text
        mailSender.send(message)
    }
}