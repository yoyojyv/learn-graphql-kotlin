package me.jerry.example.graphql.config.security

import me.jerry.example.graphql.support.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider
import javax.servlet.Filter


@Configuration
@EnableWebSecurity // Debug = true, will print the execution of the FilterChainProxy
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    prePostEnabled = true)
class GraphQLSecurityConfig(private val preAuthenticatedAuthenticationProvider: PreAuthenticatedAuthenticationProvider) : WebSecurityConfigurerAdapter() {

    companion object {
        val USER_ID_PRE_AUTH_HEADER = "user_id"
        val USER_ROLES_PRE_AUTH_HEADER = "user_roles"
    }

    val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Chapter 31: Spring Security Pre-Auth
     * When using pre-auth, you must ensure that all the graphql requests have been previously
     * authorized/authenticated by an upstream service.
     * For example, all ingress traffic to this graphql server must bypass an upstream proxy node that will validate
     * the request's JWT token. This code alone performs no authorization. Read more about Pre-auth before using this.
     */

    /**
     * Chapter 31: Spring Security Pre-Auth
     * When using pre-auth, you must ensure that all the graphql requests have been previously
     * authorized/authenticated by an upstream service.
     * For example, all ingress traffic to this graphql server must bypass an upstream proxy node that will validate
     * the request's JWT token. This code alone performs no authorization. Read more about Pre-auth before using this.
     */
    /**
     * Using pre-auth headers provide you the ability to switch or support other authentication
     * methods without making any/many application code changes. (E.g. JWT to something else)
     */

    override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(preAuthenticatedAuthenticationProvider)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        logger.info("Configuring spring security")
        http // Add the Pre Authentication Filter
            .addFilterBefore(
                createRequestHeadersPreAuthenticationFilter(),
                AbstractPreAuthenticatedProcessingFilter::class.java
            )
            .authorizeRequests() // All endpoints require authentication
            //.anyRequest().authenticated()
            .anyRequest().permitAll()  // document 를 보기위해서 품
            .and() // Disable CSRF Token generation
            .csrf().disable() // Disable the default HTTP Basic-Auth
            .httpBasic().disable() // Disable the session management filter
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and() // Disable the /logout filter
            .logout().disable() // Disable anonymous users
            .anonymous().disable()
    }

    override fun configure(web: WebSecurity) {
        web.ignoring() // Actuator health endpoint for readiness, liveness checks etc
            .antMatchers("/actuator/health") // Permit playground for development
            .antMatchers("/playground", "/vendor/playground/**") // Subscription are secured via AuthenticationConnectionListener
            .antMatchers("/subscriptions")
    }

    @Throws(Exception::class)
    private fun createRequestHeadersPreAuthenticationFilter(): Filter {
        val filter = RequestHeadersPreAuthenticationFilter()
        filter.setAuthenticationDetailsSource(GrantedAuthoritiesAuthenticationDetailsSource())
        filter.setAuthenticationManager(authenticationManager())
        filter.setContinueFilterChainOnUnsuccessfulAuthentication(false)
        return filter
    }

}
