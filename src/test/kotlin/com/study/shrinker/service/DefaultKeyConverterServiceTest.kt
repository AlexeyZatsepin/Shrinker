package com.study.shrinker.service

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class DefaultKeyConverterServiceTest {

    val service: KeyConverterService = DefaultKeyConverterService()

    @Test
    fun idMustBeConvertableBothWays(){
        val rand = Random()
        for (i in 0..1000L){
            val initId = Math.abs(rand.nextLong())
            val key = service.idToKey(initId)
            val id = service.keyToId(key)
            assertEquals(initId,id)
        }
    }
}