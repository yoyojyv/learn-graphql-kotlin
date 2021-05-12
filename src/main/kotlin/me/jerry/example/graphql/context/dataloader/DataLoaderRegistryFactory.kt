package me.jerry.example.graphql.context.dataloader

import me.jerry.example.graphql.service.BalanceService
import me.jerry.example.graphql.util.ExecutorFactory
import org.dataloader.BatchLoaderEnvironment
import org.dataloader.DataLoader
import org.dataloader.DataLoaderRegistry
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor


@Component
class DataLoaderRegistryFactory(private val balanceService: BalanceService) {

    companion object {
        val BALANCE_DATA_LOADER = "BALANCE_DATA_LOADER"
    }

    private val balanceThreadPool: Executor = ExecutorFactory.newExecutor()

    fun create(userId: String): DataLoaderRegistry {
        val registry = DataLoaderRegistry()
        registry.register(BALANCE_DATA_LOADER, createBalanceDataLoader(userId))
        return registry
    }

    private fun createBalanceDataLoader(userId: String): DataLoader<UUID, BigDecimal> {
        return DataLoader
//            .newMappedDataLoader { bankAccountIds: Set<UUID>, environment: BatchLoaderEnvironment ->
            .newMappedDataLoader { _: Set<UUID>, environment: BatchLoaderEnvironment ->
                CompletableFuture.supplyAsync(
                    {
                        balanceService.getBalanceFor(
                            environment.keyContexts as Map<*, *>,
                            userId
                        )
                    },
                    balanceThreadPool
                )
            }
    }
}
