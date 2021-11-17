package org.jdbc.setup

import org.jdbc.domain.OrderLineEvent
import java.time.ZonedDateTime
import java.time.ZonedDateTime.now


object TestDataFactory {


  fun createOrderLineEvent(
    orderId: String = "orderId",
    travelDate: ZonedDateTime? = now(),
  ): OrderLineEvent =
    OrderLineEvent(
      orderId = orderId,
      travelDate = travelDate
    )

}