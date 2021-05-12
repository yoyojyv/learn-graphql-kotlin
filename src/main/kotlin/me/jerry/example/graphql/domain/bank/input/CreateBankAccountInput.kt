package me.jerry.example.graphql.domain.bank.input

data class CreateBankAccountInput(

    // @NotBlank
    // @field:NotBlank
    val firstName: String,
    val age: Int
)
