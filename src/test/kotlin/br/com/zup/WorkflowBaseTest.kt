package br.com.zup

import br.com.zup.ApplicationConfig
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [ApplicationConfig::class])
abstract class WorkflowBaseTest
