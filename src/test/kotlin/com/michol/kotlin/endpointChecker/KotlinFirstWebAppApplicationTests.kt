package com.michol.kotlin.endpointChecker

import com.michol.kotlin.endpointChecker.tools.ConnectionTools
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class KotlinFirstWebAppApplicationTests {

	@Autowired
	lateinit var connectionTools: ConnectionTools

	@Test
	fun getStatusTestValidAddress() {
		Assert.assertEquals(200,connectionTools.getUrlStatus("http://wp.pl", 300))
	}

	@Test
	fun getStatusTestEmptyAddress() {
		Assert.assertEquals(400,connectionTools.getUrlStatus("", 300))
	}

	@Test
	fun getStatusTestValidAddressTimeout() {
		Assert.assertEquals(408,connectionTools.getUrlStatus("http://wp.pl", 1))
	}
}
