package com.icloud.authentication.filter

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter(
    @Value("\${jwt.signing.key}")
    private val signingKey: String
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (jwt != null) {
            val key = Keys.hmacShaKeyFor(signingKey.toByteArray(StandardCharsets.UTF_8))
            val claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .body
            val username = claims.get("username", String::class.java)
            val auth = UsernamePasswordAuthenticationToken(username, null, listOf(GrantedAuthority { "user" }))
            SecurityContextHolder.getContext().authentication = auth
        }
        filterChain.doFilter(request, response)
    }

    // /login 경로에 대한 요청에는 트리거 되지 않도록.
    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.servletPath == "/login"
    }
}