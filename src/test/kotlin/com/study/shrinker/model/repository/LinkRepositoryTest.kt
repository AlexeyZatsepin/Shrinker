package com.study.shrinker.model.repository

import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import com.study.shrinker.model.AbstractRepositoryTest
import com.study.shrinker.model.Link
import com.study.shrinker.model.repositories.LinkRepository
import junit.framework.Assert.*
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@DatabaseSetup(LinkRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = LinkRepositoryTest.DATASET)
open class LinkRepositoryTest : AbstractRepositoryTest() {

    @Autowired
    lateinit var repository: LinkRepository

    @Test
    fun findOneExisting(){
        val got: Optional<Link> = repository.findOne(LINK_1_ID)
        assertTrue(got.isPresent)
        val link = got.get()
        assertEquals(link,Link(LINK_1_TEXT, LINK_1_ID))
    }

    @Test
    fun findOneNotExisting(){
        val got: Optional<Link> = repository.findOne(LINK_NOT_FOUND)
        assertFalse(got.isPresent)
    }

    @Test
    fun saveToDatabase(){
        val toBeSave: Link = Link(LINK_TBS_TEXT)
        val got : Link = repository.save(toBeSave)
        val link: List<Link> = repository.findAll()
        assertTrue(link.size==4)
        assertEquals(got.text, LINK_TBS_TEXT)
    }

    companion object {
        const val DATASET = "classpath:datasets/link-table.xml"
        private val  LINK_NOT_FOUND: Long = 1L
        private val  LINK_TBS_TEXT: String = "http://ukr"
        private val  LINK_1_TEXT: String = "http://www.webpremierstudio.me"
        private val  LINK_1_ID: Long = 100500L
    }
}