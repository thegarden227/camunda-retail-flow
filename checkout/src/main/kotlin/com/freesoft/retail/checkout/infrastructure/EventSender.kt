package com.freesoft.retail.checkout.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import java.lang.RuntimeException

@Component
@EnableBinding(Source::class)
class EventSender(private val output: MessageChannel) {

    fun <T> send(event: Event<T>) {
        try {
            val objectMapper = ObjectMapper()
            val jsonMessage = objectMapper.writeValueAsString(event)
            output.send(MessageBuilder.withPayload(jsonMessage).setHeader("type", event.type).build())
        } catch (exception: Exception) {
            throw RuntimeException("Could not transform and send message due to: ${exception.message}", exception)
        }
    }
}