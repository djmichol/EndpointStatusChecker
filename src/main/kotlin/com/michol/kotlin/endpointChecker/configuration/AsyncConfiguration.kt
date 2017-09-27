package com.michol.kotlin.endpointChecker.configuration

import com.michol.kotlin.endpointChecker.components.IMessageSender
import com.michol.kotlin.endpointChecker.components.MailSender
import com.michol.kotlin.endpointChecker.exception.CustomAsyncExceptionHandler
import com.michol.kotlin.endpointChecker.repository.AddresseeMailRepository
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler
import org.springframework.scheduling.TaskScheduler



@Configuration
@EnableAsync
@EnableScheduling
class AsyncConfiguration : AsyncConfigurer{

    override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler {
        return CustomAsyncExceptionHandler()
    }

    override fun getAsyncExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 2
        executor.maxPoolSize = 2
        executor.setQueueCapacity(500)
        executor.threadNamePrefix = "EndpointChecker-"
        executor.initialize()
        return executor
    }

    @Bean
    fun taskScheduler(): TaskScheduler {
        return ConcurrentTaskScheduler()
    }

    @Bean
    fun messageSender(addresseeMailRepository: AddresseeMailRepository, javaMailSender: JavaMailSender, environment: Environment):IMessageSender{
        return MailSender(addresseeMailRepository, javaMailSender, environment)
    }
}