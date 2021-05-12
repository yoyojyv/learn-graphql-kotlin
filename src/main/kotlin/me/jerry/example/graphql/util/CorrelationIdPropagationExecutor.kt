package me.jerry.example.graphql.util

import me.jerry.example.graphql.instrumentation.RequestLoggingInstrumentation.Companion.CORRELATION_ID
import org.slf4j.MDC
import java.util.concurrent.Executor

class CorrelationIdPropagationExecutor(val delegate: Executor) : Executor {

    override fun execute(command: Runnable) {
        val correlationId = MDC.get(CORRELATION_ID)
        delegate.execute {
            try {
                MDC.put(CORRELATION_ID, correlationId)
                command.run()
            } finally {
                MDC.remove(CORRELATION_ID)
            }
        }
    }
}
