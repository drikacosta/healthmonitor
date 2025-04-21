package br.com.healthmonitor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["br.com.healthmonitor.security", "br.com.healthmonitor.repository"])
class HealthmonitorApplication

fun main(args: Array<String>) {
	runApplication<HealthmonitorApplication>(*args)
}
