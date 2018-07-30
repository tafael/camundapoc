package br.com.zup.tasks

import org.apache.logging.log4j.LogManager
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Service

@Service
open class DummyTask : JavaDelegate {

    companion object {
        val logger = LogManager.getLogger(DummyTask::class.java)
    }


    override fun execute(execution: DelegateExecution) {
        logger.info("Dummy Task ${execution.businessKey}")
    }

}
