package me.jerry.example.graphql.config.security

import me.jerry.example.graphql.config.security.GraphQLSecurityConfig.Companion.USER_ID_PRE_AUTH_HEADER
import org.apache.commons.lang3.StringUtils
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter
import javax.servlet.http.HttpServletRequest

class RequestHeadersPreAuthenticationFilter : AbstractPreAuthenticatedProcessingFilter() {

    override fun getPreAuthenticatedPrincipal(request: HttpServletRequest): Any {
        println("USER_ID_PRE_AUTH_HEADER : ${USER_ID_PRE_AUTH_HEADER}")
        println(" request.getHeader(USER_ID_PRE_AUTH_HEADER) : ${ request.getHeader(USER_ID_PRE_AUTH_HEADER)}")
        return request.getHeader(USER_ID_PRE_AUTH_HEADER)
    }

    override fun getPreAuthenticatedCredentials(request: HttpServletRequest?): Any {
        return StringUtils.EMPTY
    }

}
