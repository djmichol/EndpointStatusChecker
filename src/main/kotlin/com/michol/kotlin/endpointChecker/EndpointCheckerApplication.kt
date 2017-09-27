package com.michol.kotlin.endpointChecker

import com.michol.kotlin.endpointChecker.data.entity.Endpoint
import com.michol.kotlin.endpointChecker.repository.EndpointRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class KotlinFirstWebAppApplication{

    @Bean
    fun init(repository: EndpointRepository) = CommandLineRunner {
        repository.save(Endpoint("http://wp.pl"))
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(KotlinFirstWebAppApplication::class.java, *args)
}
