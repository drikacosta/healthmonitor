package br.com.healthmonitor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HealthmonitorApplication

fun main(args: Array<String>) {
	runApplication<HealthmonitorApplication>(*args)
}
