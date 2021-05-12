package me.jerry.example.graphql.context

import graphql.kickstart.servlet.context.GraphQLServletContext
import org.dataloader.DataLoaderRegistry
import java.util.*
import javax.security.auth.Subject
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.Part

class CustomGraphQLContext(
    val userId: String,
    val context: GraphQLServletContext
) : GraphQLServletContext {

    override fun getSubject(): Optional<Subject> {
        return context.subject
    }

    override fun getDataLoaderRegistry(): DataLoaderRegistry {
        return context.dataLoaderRegistry
    }

    override fun getFileParts(): MutableList<Part> {
        return context.fileParts
    }

    override fun getParts(): MutableMap<String, MutableList<Part>> {
        return context.parts
    }

    override fun getHttpServletRequest(): HttpServletRequest {
        return context.httpServletRequest
    }

    override fun getHttpServletResponse(): HttpServletResponse {
        return context.httpServletResponse
    }

}
