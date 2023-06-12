package com.example.gymi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class GymiApplication

fun main(args: Array<String>) {
    runApplication<GymiApplication>(*args)
}
