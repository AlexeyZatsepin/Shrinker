package com.study.shrinker.service

import com.study.shrinker.service.DefaultKeyMapperService
import com.study.shrinker.service.KeyMapperService
import org.junit.Assert.*
import org.junit.Test


class DefaultKeyMapperServiceTest {

    val service: KeyMapperService = DefaultKeyMapperService()

    private val KEY: String = "aAbBcCdD"

    private val LINK: String = "http://online.com"

    @Test fun clientCanAddNewKeyWithLink(){
        assertEquals(KeyMapperService.Add.Success(KEY, LINK), service.add(KEY,LINK))
        assertEquals(KeyMapperService.Get.Link(LINK), service.getLink(KEY))
    }

    private val  LINK_NEW: String = "a"

    @Test fun clientCanNotAddExistingKey(){
        service.add(KEY,LINK)
        assertEquals(KeyMapperService.Add.AlreadyExist(KEY), service.add(KEY,LINK_NEW))
        assertEquals(KeyMapperService.Get.Link(LINK), service.getLink(KEY))
    }

    @Test fun clientCanNotTakeLinkNotFoundInService(){
        assertEquals(KeyMapperService.Get.NotFound(KEY), service.getLink(KEY))
    }
}