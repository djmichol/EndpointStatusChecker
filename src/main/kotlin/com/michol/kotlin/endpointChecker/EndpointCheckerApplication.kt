package com.michol.kotlin.endpointChecker

import com.michol.kotlin.endpointChecker.configuration.AsyncConfiguration
import com.michol.kotlin.endpointChecker.data.entity.AddresseeMail
import com.michol.kotlin.endpointChecker.data.entity.Endpoint
import com.michol.kotlin.endpointChecker.repository.AddresseeMailRepository
import com.michol.kotlin.endpointChecker.repository.EndpointRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
@Import(AsyncConfiguration::class)
class KotlinFirstWebAppApplication{
    @Bean
    fun init(endpointRepository: EndpointRepository, addresseeMailRepository: AddresseeMailRepository) = CommandLineRunner {
        endpointRepository.save(Endpoint("http://wp.pl"))
        addresseeMailRepository.save(AddresseeMail("mic.podbielski@wp.pl"))
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(KotlinFirstWebAppApplication::class.java, *args)
}
