package me.jerry.example.graphql.config

import graphql.scalars.ExtendedScalars
import graphql.schema.GraphQLScalarType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ScalarConfig {

    @Bean
    fun nonNegativeInt(): GraphQLScalarType {
        return ExtendedScalars.NonNegativeInt
    }

    @Bean
    fun date(): GraphQLScalarType {
        return ExtendedScalars.Date
    }

    @Bean
    fun dateTime(): GraphQLScalarType {
        return ExtendedScalars.DateTime
    }

}
