package com.freesoft.retail.payment.infrastructure

import java.util.*

data class Event<T>(val type: String?,
                    val id: String,
                    val traceId: String?,
                    val sender: String?,
                    val timestamp: Date?,
                    val correlationId: String?,
                    val payload: T?) {

    constructor(type: String,
                traceId: String,
                payload: T) : this(type,
            UUID.randomUUID().toString(),
            traceId,
            "Payment",
            Date(),
            null,
            payload)
}