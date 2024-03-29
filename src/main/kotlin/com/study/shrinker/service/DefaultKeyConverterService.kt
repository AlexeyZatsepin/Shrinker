package com.study.shrinker.service

import org.springframework.stereotype.Component

@Component
class DefaultKeyConverterService : KeyConverterService {

    val chars = "qwertyuiopasdfghjklzxcvbnm,.QWERTYUIOZXCVBNM<>ASDFGHJKL-_".toCharArray()
    val charToLong = (0..chars.size-1).map { i -> Pair(chars[i], i.toLong()) }.toMap()

    override fun idToKey(id: Long): String {
        var n = id
        var builder = StringBuilder()
        while(n != 0L){
            builder.append(chars[(n%chars.size).toInt()])
            n /= chars.size
        }
        return builder.reverse().toString()
    }

    override fun keyToId(key: String): Long = key.map {c -> charToLong[c]!! }.fold(0L, {a,b -> a*chars.size + b})
}