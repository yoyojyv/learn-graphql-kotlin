package me.jerry.example.graphql.listener

import graphql.kickstart.servlet.core.GraphQLServletListener
import me.jerry.example.graphql.support.Logger
import org.slf4j.MDC
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.Instant
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoggingListener(private val clock: Clock) : GraphQLServletListener {

    companion object: Logger()

    override fun onRequest(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): GraphQLServletListener.RequestCallback {

        val startTime = Instant.now(clock)

        logger.info("LoggingListener.onRequest()... startTime : {}", startTime)

        return object : GraphQLServletListener.RequestCallback {

            override fun onSuccess(request: HttpServletRequest, response: HttpServletResponse) {
                // no-op
            }

            override fun onError(
                request: HttpServletRequest, response: HttpServletResponse,
                throwable: Throwable
            ) {
                logger.error("Caught exception in listener.", throwable)
            }

            override fun onFinally(request: HttpServletRequest, response: HttpServletResponse) {
                // This callback will be called post graphql lifecycle.
                // If we are multi-threading we can clear the original NIO thread MDC variables here.
                MDC.clear()
            }
        }
    }

}
