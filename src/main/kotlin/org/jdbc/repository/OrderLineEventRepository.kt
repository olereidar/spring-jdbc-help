package org.jdbc.repository

import org.jdbc.domain.OrderLineEvent
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.ZonedDateTime

@Repository
interface OrderLineEventRepository : CrudRepository<OrderLineEvent, Long> {

  @Query(value = "select * from order_line_event ole where ole.order_id = :orderId ")
  fun findByOrderId(orderId: String) : List<OrderLineEvent>

}