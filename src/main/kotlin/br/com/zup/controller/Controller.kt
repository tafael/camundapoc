package br.com.zup.controller

import org.apache.logging.log4j.LogManager
import org.camunda.bpm.engine.RuntimeService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class Controller(
    private val runtimeService: RuntimeService
) {

    companion object {
        val logger = LogManager.getLogger(Controller::class.java)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @RequestMapping(
        "/create",
        method = [(RequestMethod.POST)]
    )
    fun create(): String {
        val businessKey = "${UUID.randomUUID()}"
        runtimeService.startProcessInstanceByKey("send_receive", businessKey, mapOf("businessKey" to businessKey))
        return businessKey
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(
        "/send/{businessKey}",
        method = [(RequestMethod.POST)]
    )
    fun send(
        @PathVariable("businessKey") businessKey: String
    ): String {
        try {
            runtimeService.correlateMessage("Message", businessKey)
            logger.info("Message sent with SUCCESS")
            return "success"
        } catch (e: Exception) {
            logger.info("--- MESSAGE NOT SENT ---")
            logger.error(e.message)
            return "error"
        }
    }

}
