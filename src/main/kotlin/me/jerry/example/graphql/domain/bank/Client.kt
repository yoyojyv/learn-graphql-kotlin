package me.jerry.example.graphql.domain.bank

import java.util.*

data class Client(
    val id: UUID,
    val firstName: String,
    val lastName: String,
)
