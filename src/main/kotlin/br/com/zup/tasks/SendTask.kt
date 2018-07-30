package br.com.zup.tasks

import org.apache.logging.log4j.LogManager
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Service

@Service
open class SendTask(
    val runtimeService: RuntimeService
) : JavaDelegate {

    companion object {
        val logger = LogManager.getLogger(SendTask::class.java)
    }

    override fun execute(execution: DelegateExecution) {
        val businessKey = execution.getVariable("businessKey") as String
        logger.info("SendTask ${businessKey}")
        try {
            runtimeService.correlateMessage("Message", businessKey)
            logger.info("Message sent with SUCCESS")
        } catch (e: Exception) {
            logger.info("--- MESSAGE NOT SENT ---")
            logger.error(e.message)
        }
    }
}

