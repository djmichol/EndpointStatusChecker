package com.michol.kotlin.endpointChecker.tools

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL


@Component
class ConnectionTools {

    fun getUrlStatus(url: String, timeout: Int): Int {
        return try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.connectTimeout = timeout
            connection.readTimeout = timeout
            connection.requestMethod = "HEAD"
            connection.responseCode
        } catch (exception: SocketTimeoutException) {
            HttpStatus.REQUEST_TIMEOUT.value()
        } catch (exception: Exception) {
            HttpStatus.BAD_REQUEST.value()
        }
    }
}