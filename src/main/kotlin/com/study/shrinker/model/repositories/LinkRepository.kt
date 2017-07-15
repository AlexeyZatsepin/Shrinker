package com.study.shrinker.model.repositories

import com.study.shrinker.model.Link
import org.springframework.data.repository.Repository
import java.util.*

interface LinkRepository : Repository<Link,Long> {
    fun findOne(linK_1_ID: Long?): Optional<Link>
    fun save(toBeSave: Link): Link
    fun findAll(): List<Link>
}