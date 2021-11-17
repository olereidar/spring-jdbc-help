package org.jdbc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories

@SpringBootApplication
@EnableJdbcRepositories
class SpringJdbcApplication

fun main(args: Array<String>) {
  runApplication<SpringJdbcApplication>(*args)
}
