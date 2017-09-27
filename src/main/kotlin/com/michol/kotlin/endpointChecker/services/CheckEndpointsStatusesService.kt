package com.michol.kotlin.endpointChecker.services

import com.michol.kotlin.endpointChecker.components.IMessageSender
import com.michol.kotlin.endpointChecker.data.response.EndpointStatusResponse
import com.michol.kotlin.endpointChecker.repository.EndpointRepository
import com.michol.kotlin.endpointChecker.tools.ConnectionTools
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.function.Consumer

@Service
class CheckEndpointsStatusesService(
        private val connectionTools: ConnectionTools,
        private val endpointRepository: EndpointRepository,
        private val messageSender : IMessageSender
)
{
    private val logger = LoggerFactory.getLogger(CheckEndpointsStatusesService::class.java)

    @Async
    @Scheduled(fixedDelay = 6000)
    fun checkStatuses(){
        logger.info("Check status start")
        val endpoints = endpointRepository.findAll()
        endpoints.forEach(Consumer { endpoint ->
            run {
                val status = connectionTools.getUrlStatus(endpoint.url, 300)
                logger.info(endpoint.url + " status " + status.toString())
                if (status != 200) messageSender.sendMessage(EndpointStatusResponse(HttpStatus.valueOf(status), endpoint.url).toString(),endpoint.url)
            }
        })
    }

}