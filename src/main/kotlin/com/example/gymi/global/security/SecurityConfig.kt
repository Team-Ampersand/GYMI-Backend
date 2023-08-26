package com.example.gymi.global.security

import com.example.gymi.global.security.filter.JwtExceptionFilter
import com.example.gymi.global.security.filter.JwtRequestFilter
import com.example.gymi.global.security.handler.CustomAccessDeniedHandler
import com.example.gymi.global.security.handler.CustomAuthenticationEntryPointHandler
import com.example.gymi.global.security.jwt.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.web.cors.CorsUtils

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val tokenProvider: TokenProvider,
    private val jwtExceptionFilter: JwtExceptionFilter,
    private val jwtRequestFilter: JwtRequestFilter
) {

    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain {
        return http
            .cors().and()
            .csrf().disable()
            .formLogin().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

            .authorizeRequests()
            .requestMatchers(RequestMatcher { request ->
                CorsUtils.isPreFlightRequest(request)
            }).permitAll()

            .antMatchers("/auth/**").permitAll()

            .antMatchers(HttpMethod.POST, "/notice").hasAuthority("ROLE_ADMIN")
            .antMatchers(HttpMethod.DELETE, "/notice/{id}").hasAuthority("ROLE_ADMIN")
            .antMatchers(HttpMethod.PATCH, "/notice/{id}").hasAuthority("ROLE_ADMIN")

            .anyRequest().denyAll()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(CustomAccessDeniedHandler())
            .authenticationEntryPoint(CustomAuthenticationEntryPointHandler())

            .and()
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(jwtExceptionFilter, JwtRequestFilter::class.java)

            .build()
    }
}