package org.jdbc.setup

import org.jdbc.repository.OrderLineEventRepository
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.containers.PostgreSQLContainer

@SpringBootTest
@ActiveProfiles( "local")
@ContextConfiguration(initializers = [DbIntegrationTestCase.Initializer::class])
@ExtendWith(SpringExtension::class)
abstract class DbIntegrationTestCase {

  @Autowired
  protected lateinit var orderLineEventRepository: OrderLineEventRepository

  class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
      postgresContainer.start() //Tear down will happen on JVM exit
      val values = TestPropertyValues.of(
        "spring.datasource.url=${postgresContainer.jdbcUrl}"
      )
      values.applyTo(configurableApplicationContext.environment)
    }
  }

  companion object {
    class KGenericContainer(imageName: String) : PostgreSQLContainer<KGenericContainer>(imageName)
    internal var postgresContainer: KGenericContainer = KGenericContainer("postgres:11-alpine")
      .withDatabaseName("jdbc")
      .withUsername("jdbc")
      .withPassword("1814")
      .withExposedPorts(5432)
  }

}