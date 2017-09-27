package com.michol.kotlin.endpointChecker.configuration

import com.michol.kotlin.endpointChecker.exception.CustomAsyncExceptionHandler
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
}