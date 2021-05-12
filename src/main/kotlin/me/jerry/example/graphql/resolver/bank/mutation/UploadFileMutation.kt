package me.jerry.example.graphql.resolver.bank.mutation

import graphql.kickstart.servlet.context.DefaultGraphQLServletContext
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.schema.DataFetchingEnvironment
import me.jerry.example.graphql.support.Logger
import org.springframework.stereotype.Component
import java.util.*
@Component
class UploadFileMutation : GraphQLMutationResolver {

    companion object: Logger()

    /**
     * POST http://localhost:8080/graphql
     *
     * operations {"query": "mutation { uploadFile }", "variables" : {}}
     * file -> attach
     *
     */
    fun uploadFile(env: DataFetchingEnvironment) : UUID {
        logger.info("Uploading file")
        val context = env.getContext<DefaultGraphQLServletContext>()
        context.fileParts
            .forEach { part ->
                logger.info("uploading: {}, size: {}", part.submittedFileName, part.size)
            }
        return UUID.randomUUID()
    }

}
