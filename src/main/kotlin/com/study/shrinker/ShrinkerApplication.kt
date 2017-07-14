package com.study.shrinker

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ShrinkerApplication

fun main(args: Array<String>) {
    SpringApplication.run(ShrinkerApplication::class.java, *args)
}
