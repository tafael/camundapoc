package br.com.zup.tasks

import org.apache.logging.log4j.LogManager
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
open class SendTask(
    val runtimeService: RuntimeService,
    @Value("\${send.task.enabled:false}")
    val sendTaskEnabled: Boolean
) : JavaDelegate {

    companion object {
        val logger = LogManager.getLogger(SendTask::class.java)
    }

    override fun execute(execution: DelegateExecution) {
        val businessKey = execution.getVariable("businessKey") as String
        if (sendTaskEnabled) {
            logger.info("Send Task ${businessKey}")
            try {
                runtimeService.correlateMessage("Message", businessKey)
                logger.info("Message sent with SUCCESS")
            } catch (e: Exception) {
                logger.info("--- MESSAGE NOT SENT ---")
                logger.error(e.message)
            }
        } else {
            logger.info("Send Task disabled.")
        }

    }
}

