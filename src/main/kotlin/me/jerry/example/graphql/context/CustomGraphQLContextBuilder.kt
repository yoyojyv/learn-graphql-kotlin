package me.jerry.example.graphql.context

import graphql.kickstart.execution.context.GraphQLContext
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder
import me.jerry.example.graphql.context.dataloader.DataLoaderRegistryFactory
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.websocket.Session
import javax.websocket.server.HandshakeRequest


@Component
class CustomGraphQLContextBuilder(
    private val dataLoaderRegistryFactory: DataLoaderRegistryFactory
) : GraphQLServletContextBuilder {

    override fun build(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): GraphQLContext {
        val userId= httpServletRequest.getHeader("user_id")

        val context = DefaultGraphQLServletContext.createServletContext()
            .with(httpServletRequest)
            .with(httpServletResponse)
            .with(dataLoaderRegistryFactory.create(userId))
            .build()

        return CustomGraphQLContext(userId, context)
    }

    override fun build(session: Session, handshakeRequest: HandshakeRequest): GraphQLContext {
        return DefaultGraphQLWebSocketContext.createWebSocketContext()
            .with(session)
            .with(handshakeRequest)
            .build();
    }

    override fun build(): GraphQLContext {
        throw IllegalStateException("Unsupported")
    }

}
