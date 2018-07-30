package br.com.zup

import org.camunda.bpm.engine.RuntimeService
import org.springframework.beans.factory.annotation.Autowired
import java.util.UUID
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.test.Test
import kotlin.test.assertTrue

class SendReceiveTest : WorkflowBaseTest() {

    @Autowired
    lateinit var runtimeService: RuntimeService

    @Autowired
    lateinit var countDownLatch: CountDownLatch

    @Test
    fun testSendReceiveWorkflow() {
        for (i in 0..999) {
            startProcess()
        }
        assertTrue(countDownLatch.await(100, TimeUnit.SECONDS))
    }

    fun startProcess() {
        val businessKey = "${UUID.randomUUID()}"
        runtimeService.startProcessInstanceByKey("send_receive", businessKey, mapOf("businessKey" to businessKey))
    }

}
