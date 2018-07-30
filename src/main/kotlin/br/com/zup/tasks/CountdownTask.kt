package br.com.zup.tasks

import org.apache.logging.log4j.LogManager
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Service
import java.util.concurrent.CountDownLatch

@Service
open class CountdownTask(
    val countDownLatch: CountDownLatch
) : JavaDelegate {

    companion object {
        val logger = LogManager.getLogger(CountdownTask::class.java)
    }

    override fun execute(execution: DelegateExecution) {
        logger.info("CountdownTask ${execution.businessKey}")
        countDownLatch.countDown()
        logger.info("Lasting ${countDownLatch.count}")
    }

}
