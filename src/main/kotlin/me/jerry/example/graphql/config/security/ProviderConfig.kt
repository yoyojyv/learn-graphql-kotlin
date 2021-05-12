package me.jerry.example.graphql.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesUserDetailsService

@Configuration
class ProviderConfig {

    @Bean
    fun preAuthenticatedAuthenticationProvider(): PreAuthenticatedAuthenticationProvider {
        val preAuthProvider = PreAuthenticatedAuthenticationProvider()
        preAuthProvider.setPreAuthenticatedUserDetailsService(
            PreAuthenticatedGrantedAuthoritiesUserDetailsService()
        )
        return preAuthProvider
    }
}
