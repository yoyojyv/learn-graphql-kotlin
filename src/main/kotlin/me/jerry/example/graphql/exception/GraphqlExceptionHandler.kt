package me.jerry.example.graphql.exception

import graphql.GraphQLException
import graphql.kickstart.spring.error.ThrowableGraphQLError
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * graphql.servlet.exception-handlers-enabled: true 일때 사용
 */
@Component
class GraphqlExceptionHandler {

    @ExceptionHandler(GraphQLException::class) // , ConstraintViolationException::class
    fun handle(e: GraphQLException): ThrowableGraphQLError {
        return ThrowableGraphQLError(e)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handle(e: RuntimeException): ThrowableGraphQLError {
        return ThrowableGraphQLError(e, "ExceptionHandler -> RuntimeException")
    }

}
