package org.jdbc.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime

@Configuration
class DataJdbcConfiguration : AbstractJdbcConfiguration() {

  @WritingConverter
  class ZonedDateTimeWriterConverter : Converter<ZonedDateTime, Timestamp> {

    override fun convert(date: ZonedDateTime): Timestamp {
      return Timestamp.valueOf(date.withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())
    }

  }

  @ReadingConverter
  class ZonedDateTimeReaderConverter : Converter<Timestamp, ZonedDateTime> {

    override fun convert(sqlTimestamp: Timestamp): ZonedDateTime {
      return sqlTimestamp.toLocalDateTime().atZone(ZoneOffset.UTC)
    }

  }

  @Bean
  override fun jdbcCustomConversions(): JdbcCustomConversions {
    return JdbcCustomConversions(
      listOf(
        ZonedDateTimeWriterConverter(),
        ZonedDateTimeReaderConverter(),
      )
    )
  }
}