package com.michol.kotlin.endpointChecker.services

import com.michol.kotlin.endpointChecker.data.response.EndpointStatusResponse
import com.michol.kotlin.endpointChecker.repository.EndpointRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.function.Consumer

@Service
class CheckEndpointsStatusesService(
        private val connectionTools: ConnectionTools,
        private val endpointRepository: EndpointRepository,
        private val mailSender: MailSender
)
{
    private val logger = LoggerFactory.getLogger(CheckEndpointsStatusesService::class.java)

    @Async
    @Scheduled(fixedDelay = 5000)
    fun checkStatuses(){
        val endpoints = endpointRepository.findAll()
        endpoints.forEach(Consumer { endpoint ->
            run {
                var status = connectionTools.getUrlStatus(endpoint.url, 300)
                logger.info(endpoint.url+" status "+status.toString())
                if (status != 200) mailSender.sendMessage(EndpointStatusResponse(HttpStatus.valueOf(status), endpoint.url).toString())
            }
        })
    }

}