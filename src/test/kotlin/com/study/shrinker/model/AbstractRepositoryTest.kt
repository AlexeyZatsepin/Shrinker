package com.study.shrinker.model

import com.github.springtestdbunit.DbUnitTestExecutionListener
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestExecutionListeners
//import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@TestExecutionListeners(DbUnitTestExecutionListener::class)
@DirtiesContext
abstract class AbstractRepositoryTest : AbstractTransactionalJUnit4SpringContextTests() {
}