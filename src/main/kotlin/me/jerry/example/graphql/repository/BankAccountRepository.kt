package me.jerry.example.graphql.repository

import me.jerry.example.graphql.domain.bank.BankAccount
import me.jerry.example.graphql.domain.bank.Currency
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.UUID
import java.util.UUID.fromString

@Component
class BankAccountRepository {

    private val bankAccounts: List<BankAccount> = listOf(
        BankAccount(
            id = fromString("c6aa269a-812b-49d5-b178-a739a1ed74cc"),
            currency = Currency.USD,
            createdAt = ZonedDateTime.parse("2019-05-03T12:12:00+00:00")
        ),
        BankAccount(
            id = fromString("410f5919-e50b-4790-aae3-65d2d4b21c77"),
            currency = Currency.USD,
            createdAt = ZonedDateTime.parse("2020-12-03T10:15:30+00:00")
        ),

        BankAccount(
            id = fromString("024bb503-5c0f-4d60-aa44-db19d87042f4"),
            currency = Currency.USD,
            createdAt = ZonedDateTime.parse("2020-12-03T10:15:31+00:00")
        ),


        BankAccount(
            id = fromString("48e4a484-af2c-4366-8cd4-25330597473f"),
            currency = Currency.USD,
            createdAt = ZonedDateTime.parse("2007-08-07T19:01:22+04:00")
        ),

    )
        .sortedBy { it.id }

    fun getBankAccounts(): List<BankAccount> {
        return bankAccounts
    }

    fun getBankAccountsAfter(id: UUID?): List<BankAccount> {
        return bankAccounts
            .dropWhile { (id1) -> id1.compareTo(id) != 1 }
    }

}
