package com.study.shrinker.controllers

import com.study.shrinker.service.KeyMapperService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
@RunWith(SpringRunner::class)
@SpringBootTest
@WebAppConfiguration
class RedirectControllerTest {

    @Autowired lateinit var context: WebApplicationContext

    lateinit var mock: MockMvc

    @Mock
    lateinit var service: KeyMapperService

    @Autowired
    @InjectMocks
    lateinit var controller: RedirectController

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        mock = MockMvcBuilders.webAppContextSetup(context).build()

        Mockito.`when`(service.getLink(TEST_URL)).thenReturn(KeyMapperService.Get.Link(HEADER_VALUE))
        Mockito.`when`(service.getLink(BAD_URL)).thenReturn(KeyMapperService.Get.NotFound(HEADER_VALUE))
    }

    private val TEST_URL = "aAbBcCdD"
    private val BAD_URL = "a"
    private val REDIRECT_STATUS:Int = 302
    private val NOT_FOUND:Int = 404
    private val HEADER_NAME = "Location"
    private val HEADER_VALUE = "http://www.online.com"

    @Test fun redirectIfRequestSuccess(){
        mock.perform(get("/$TEST_URL"))
                .andExpect(status().`is`(REDIRECT_STATUS))
                .andExpect(header().string(HEADER_NAME,HEADER_VALUE))
    }

    @Test fun return404IfBadKey(){
        mock.perform(get("/$BAD_URL"))
                .andExpect(status().`is`(NOT_FOUND))
    }

}
