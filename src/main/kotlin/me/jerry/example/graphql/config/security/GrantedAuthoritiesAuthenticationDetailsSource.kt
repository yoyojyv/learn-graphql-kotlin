package me.jerry.example.graphql.config.security

import me.jerry.example.graphql.config.security.GraphQLSecurityConfig.Companion.USER_ROLES_PRE_AUTH_HEADER
import org.springframework.security.authentication.AuthenticationDetailsSource
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails
import javax.servlet.http.HttpServletRequest

class GrantedAuthoritiesAuthenticationDetailsSource : AuthenticationDetailsSource<HttpServletRequest, PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails> {

    override fun buildDetails(
        request: HttpServletRequest
    ): PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails {
        val userRoles = request.getHeader(USER_ROLES_PRE_AUTH_HEADER) ?: ""
        val authorities = GrantedAuthorityFactory.getAuthoritiesFrom(userRoles)
        return PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails(request, authorities)
    }

}
