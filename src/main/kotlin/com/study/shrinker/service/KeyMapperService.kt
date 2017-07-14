package com.study.shrinker.service


interface KeyMapperService {
    interface Add {
        data class Success(val key: String, val link: String) : Add
        data class AlreadyExist(val key: String) : Add
    }

    interface Get {
        data class Link(val link: String) : Get
        data class NotFound(val link: String) : Get
    }

    fun add(link: String): String
    fun getLink(key: String): Get
}