package de.bringmeister.connect.product

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAutoConfiguration
@EnableAsync
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
