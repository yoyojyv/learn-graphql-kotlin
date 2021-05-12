package me.jerry.example.graphql.config.security

import org.apache.commons.lang3.StringUtils
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors

class GrantedAuthorityFactory {

    companion object {
        fun getAuthoritiesFrom(userRoles: String): List<GrantedAuthority> {
            return if (StringUtils.isBlank(userRoles)) {
                emptyList()
            } else mutableSetOf(*userRoles.split(",".toRegex()).toTypedArray())
                .stream()
                .map { role: String? -> SimpleGrantedAuthority(role) }
                .collect(Collectors.toList())
        }

    }

}
