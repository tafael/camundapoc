package br.com.zup

import org.camunda.bpm.engine.RuntimeService
import org.springframework.beans.factory.annotation.Autowired
import java.util.UUID
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.test.Test
import kotlin.test.assertTrue

class SendReceiveTest : WorkflowBaseTest() {

    @Autowired
    lateinit var runtimeService: RuntimeService

    @Autowired
    lateinit var countDownLatch: CountDownLatch

    @Test
    fun testSendReceiveWorkflow() {
        performTest(20, 200)
    }

    private fun performTest(threads: Int, intervalMillis: Long) {
        var threadCount = countDownLatch.count

        for (i in 0..((threadCount / threads))) {
            for (j in 0..(threads - 1)) {
                if (threadCount > 0) {
                    threadCount--
                    thread {
                        startProcess()
                    }
                }
            }
            Thread.sleep(intervalMillis)
        }
        assertTrue(countDownLatch.await(100, TimeUnit.SECONDS))
    }

    fun startProcess() {
        val businessKey = "${UUID.randomUUID()}"
        runtimeService.startProcessInstanceByKey("send_receive", businessKey, mapOf("businessKey" to businessKey))
    }

}
