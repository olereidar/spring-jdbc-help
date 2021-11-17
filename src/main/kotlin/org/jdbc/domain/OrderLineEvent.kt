package org.jdbc.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.util.*

@Table("order_line_event")
data class OrderLineEvent(
  @Id
  val id: Long? = null,
  val orderId: String,
  val travelDate: ZonedDateTime? = null, // TODO https://github.com/spring-projects/spring-data-jdbc/issues/1089
  val timestamp:  LocalDateTime = LocalDateTime.now(),
)