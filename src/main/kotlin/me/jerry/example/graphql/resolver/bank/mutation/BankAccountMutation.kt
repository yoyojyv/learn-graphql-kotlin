package me.jerry.example.graphql.resolver.bank.mutation

import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.schema.DataFetchingEnvironment
import me.jerry.example.graphql.domain.bank.BankAccount
import me.jerry.example.graphql.domain.bank.Currency
import me.jerry.example.graphql.domain.bank.input.CreateBankAccountInput
import me.jerry.example.graphql.support.Logger
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*

@Component
class BankAccountMutation(
    private val clock: Clock
) : GraphQLMutationResolver {

    companion object : Logger()

    fun createBankAccount(input: CreateBankAccountInput, environment: DataFetchingEnvironment): BankAccount {
        // DataFetchingEnvironment 내용 디버거로 확인해볼 것
        // ex: environment.selectionSet.contains("id")
        // ex: environment.arguments
        logger.info("Creating bank account for {}", input)

        return BankAccount(
            id = UUID.randomUUID(),
            currency = Currency.KWR,
            createdOn = LocalDate.now(clock),
            createdAt = ZonedDateTime.now(clock)
        )
    }

}
