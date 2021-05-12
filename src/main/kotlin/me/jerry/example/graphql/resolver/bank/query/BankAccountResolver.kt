package me.jerry.example.graphql.resolver.bank.query

import graphql.kickstart.tools.GraphQLResolver
import graphql.schema.DataFetchingEnvironment
import me.jerry.example.graphql.context.dataloader.DataLoaderRegistryFactory
import me.jerry.example.graphql.domain.bank.BankAccount
import me.jerry.example.graphql.support.Logger
import org.dataloader.DataLoader
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.*
import java.util.concurrent.CompletableFuture


@Component
class BankAccountResolver : GraphQLResolver<BankAccount> {

    companion object: Logger()

//    fun balance(bankAccount: BankAccount): BigDecimal {
//        logger.info("Getting balance for {}", bankAccount.id)
//        return BigDecimal.ONE
//    }

    // N + 1 문제
    fun balance(bankAccount: BankAccount, environment: DataFetchingEnvironment): CompletableFuture<BigDecimal> {
        val dataLoader: DataLoader<UUID, BigDecimal> = environment
            .getDataLoader(DataLoaderRegistryFactory.BALANCE_DATA_LOADER)
        return dataLoader.load(bankAccount.id, bankAccount)
    }

}
