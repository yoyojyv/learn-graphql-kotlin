package me.jerry.example.graphql.instrumentation

import graphql.ExecutionResult
import graphql.execution.instrumentation.InstrumentationContext
import graphql.execution.instrumentation.SimpleInstrumentation
import graphql.execution.instrumentation.SimpleInstrumentationContext
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.Duration
import java.time.Instant


@Component
class RequestLoggingInstrumentation(private val clock: Clock) : SimpleInstrumentation() {

    companion object {
        val CORRELATION_ID = "correlation_id"
    }

    val logger = LoggerFactory.getLogger(this.javaClass)

    override fun beginExecution(
        parameters: InstrumentationExecutionParameters
    ): InstrumentationContext<ExecutionResult>? {
        val start = Instant.now(clock)
        // Add the correlation ID to the NIO thread
        MDC.put(CORRELATION_ID, parameters.getExecutionInput().getExecutionId().toString())
        logger.info("Query: {} with variables: {}", parameters.getQuery(), parameters.getVariables())
        return SimpleInstrumentationContext.whenCompleted { executionResult: ExecutionResult?, throwable: Throwable? ->
            val duration = Duration.between(start, Instant.now(clock))
            if (throwable == null) {
                logger.info("Completed successfully in: {}", duration)
            } else {
                logger.warn("Failed in: {}", duration, throwable)
            }
            // If we have async resolvers, this callback can occur in the thread-pool and not the NIO thread.
            // In that case, the LoggingListener will be used as a fallback to clear the NIO thread.
            MDC.clear()
        }
    }

}
