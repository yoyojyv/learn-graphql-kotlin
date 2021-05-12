package me.jerry.example.graphql.util

import java.util.concurrent.Executor
import java.util.concurrent.Executors

final class ExecutorFactory {
    companion object {
        fun newExecutor(): Executor {
            val realExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
//            val securityDelegatingExecutor = DelegatingSecurityContextExecutorService(realExecutor)
//            return CorrelationIdPropagationExecutor.wrap(securityDelegatingExecutor)
            return realExecutor
        }
    }

}
