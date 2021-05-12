package me.jerry.example.graphql.resolver.bank.query

import graphql.kickstart.tools.GraphQLQueryResolver
import graphql.relay.*
import graphql.schema.DataFetchingEnvironment
import me.jerry.example.graphql.repository.BankAccountRepository
import me.jerry.example.graphql.connection.CursorUtil
import me.jerry.example.graphql.context.CustomGraphQLContext
import me.jerry.example.graphql.domain.bank.BankAccount
import me.jerry.example.graphql.domain.bank.Currency
import me.jerry.example.graphql.support.Logger
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component
import java.util.*


@Component
class BankAccountQueryResolver(
    private val bankAccountRepository: BankAccountRepository,
    private val cursorUtil: CursorUtil,
) : GraphQLQueryResolver {

    companion object : Logger()

    @PreAuthorize("hasAuthority('get:bank_account')")
    fun bankAccount(id: UUID?, environment: DataFetchingEnvironment): BankAccount {
        logger.info("Retrieving bank account id : {}", id)

        val context = environment.getContext<CustomGraphQLContext>()
        logger.info("User ID : {}", context.userId)

        val requestedFields = environment.selectionSet.fields.map {
            it.name
        }
        logger.info("requestedFields : {}", requestedFields)

        if (environment.selectionSet.contains("specialField")) {
            // do special stuff
        }
        return BankAccount(
            id = id!!,
            currency = Currency.KWR //,
//            client = Client(
//                id = UUID.randomUUID(),
//                firstName = "Jerry",
//                lastName = "Kim"
//            )
        )
    }


    fun bankAccounts(first: Int, cursor: String? = null): Connection<BankAccount> {

//        val edges = getBankAccounts(cursor)
//            .map { bankAccount -> DefaultEdge(bankAccount, cursorUtil.from(bankAccount.id)) }
//            .take(first)
//
//        val pageInfo = DefaultPageInfo(
//            cursorUtil.getFirstCursorFrom(edges),
//            cursorUtil.getLastCursorFrom(edges),
//            cursor != null,
//            edges.size >= first
//        )
//
//        return DefaultConnection(edges, pageInfo)
//

        val edges: List<Edge<BankAccount>> = getBankAccounts(cursor)
            .map { bankAccount ->
                DefaultEdge(
                    bankAccount,
                    cursorUtil.createCursorWith(bankAccount.id)
                )
            }
            .take(first)

        val pageInfo = DefaultPageInfo(
            cursorUtil.getFirstCursorFrom(edges),
            cursorUtil.getLastCursorFrom(edges),
            cursor != null,
            edges.size >= first
        )

        return DefaultConnection(edges, pageInfo)
    }

    fun getBankAccounts(cursor: String?): List<BankAccount> {
        return if (cursor == null) {
            bankAccountRepository.getBankAccounts()
        } else bankAccountRepository.getBankAccountsAfter(cursorUtil.decode(cursor))
    }

}
