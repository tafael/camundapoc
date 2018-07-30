package br.com.zup

import org.apache.logging.log4j.LogManager
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.CountDownLatch

@SpringBootApplication
@EnableProcessApplication
@Configuration
open class ApplicationConfig() {

    @Bean
    open fun countdownLatch(): CountDownLatch = CountDownLatch(1000)

}

private val logger = LogManager.getLogger(ApplicationConfig::class.java)

fun main(args: Array<String>) {
    val app = SpringApplication.run(ApplicationConfig::class.java, *args)

    logger.info(
        """|
                   |------------------------------------------------------------
                   |Application camunda poc is running!
                   |------------------------------------------------------------""".trimMargin()
    )

}

