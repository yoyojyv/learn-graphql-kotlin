package me.jerry.example.graphql.resolver.bank

import graphql.kickstart.tools.GraphQLResolver
import me.jerry.example.graphql.domain.bank.Asset
import me.jerry.example.graphql.domain.bank.BankAccount
import me.jerry.example.graphql.support.Logger
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Component
class AssetResolver : GraphQLResolver<BankAccount> {

    companion object : Logger()

    private val executorService: ExecutorService =
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

    fun assets(bankAccount: BankAccount): CompletableFuture<List<Asset>> {
        return CompletableFuture.supplyAsync(
            {
                logger.info("Getting assets for bank account id : {}", bankAccount.id)
                listOf()
            }, executorService)
    }

}
