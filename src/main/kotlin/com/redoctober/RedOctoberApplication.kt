package com.redoctober

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan("com.redoctober.entity")
class RedoctoberApplication

fun main(args: Array<String>) {
    runApplication<RedoctoberApplication>(*args)
}
