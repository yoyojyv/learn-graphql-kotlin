package me.jerry.example.graphql.resolver.bank

import graphql.GraphQLException
import graphql.execution.DataFetcherResult
import graphql.kickstart.execution.error.GenericGraphQLError
import graphql.kickstart.tools.GraphQLQueryResolver
import graphql.kickstart.tools.GraphQLResolver
import me.jerry.example.graphql.domain.bank.BankAccount
import me.jerry.example.graphql.domain.bank.Client
import me.jerry.example.graphql.support.Logger
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Component
class ClientResolver : GraphQLResolver<BankAccount> {

    companion object : Logger()

//    fun client(bankAccount: BankAccount): Client {
//
//        logger.info("Requesting client data for bank account id : {}", bankAccount.id)
//
//        // throw GraphQLException("Client unavailable")
//        // throw RuntimeException("RuntimeException....")
//
//        return Client(
//            id = UUID.randomUUID(),
//            firstName = "Jerry",
//            lastName = "Kim"
//        )
//    }

//    fun client(bankAccount: BankAccount): DataFetcherResult<Client> {
//
//        /**
//         * 1: call multiple services
//         * 2: call another graphql server
//         * 3: call service that returns partial responses
//         */
//
//        return DataFetcherResult.newResult<Client>()
//            .data(
//                Client(
//                    id = UUID.randomUUID(),
//                    firstName = "Jerry",
//                    lastName = "Kim"
//                )
//            )
//            .error(GenericGraphQLError("Could not get sub client-id"))
//            .build()
//    }

    private val executorService: ExecutorService =
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

    fun client(bankAccount: BankAccount): CompletableFuture<Client> {

        logger.info("Out CompletableFuture...")

        return CompletableFuture.supplyAsync(
            {
                logger.info("In CompletableFuture.supplyAsync...")
                Client(
                    id = UUID.randomUUID(),
                    firstName = "Jerry",
                    lastName = "Kim"
                )
            },
            executorService
        )
    }

}
