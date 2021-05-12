package me.jerry.example.graphql.domain.bank

import java.math.BigDecimal
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*

data class BankAccount(
    val id: UUID,
    var client: Client? = null,
    val currency: Currency,
    var assets: List<Asset> = emptyList(),
    var createdOn: LocalDate? = null,
    var createdAt: ZonedDateTime? = null,
    var balance: BigDecimal? = null
)
