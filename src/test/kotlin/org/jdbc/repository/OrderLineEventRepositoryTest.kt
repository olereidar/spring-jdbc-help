package org.jdbc.repository

import org.jdbc.setup.DbIntegrationTestCase
import org.jdbc.setup.TestDataFactory
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.transaction.annotation.Transactional

class OrderLineEventRepositoryTest : DbIntegrationTestCase() {

  @Test
  @Transactional
  fun `Should get order line event based on id`() {
    val orderLineEvent = TestDataFactory.createOrderLineEvent().let { orderLineEventRepository.save(it) }
    val orderLineEvents = orderLineEventRepository.findByOrderId(orderLineEvent.orderId)
    assertThat(orderLineEvents.size, equalTo(1))
  }

}