package com.michol.kotlin.endpointChecker.configuration

import com.michol.kotlin.endpointChecker.components.IMessageSender
import com.michol.kotlin.endpointChecker.components.MailSender
import com.michol.kotlin.endpointChecker.repository.AddresseeMailRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.mail.javamail.JavaMailSender

@Configuration
class MessageSenderConfiguration {

    @Bean
    fun messageSender(addresseeMailRepository: AddresseeMailRepository, javaMailSender: JavaMailSender, environment: Environment): IMessageSender {
        return MailSender(addresseeMailRepository, javaMailSender, environment)
    }
}