package com.example.gymi.global.security.auth

import com.example.gymi.domain.user.exception.UserNotFoundException
import com.example.gymi.domain.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailService(

        private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        val user = userRepository.findByEmail(username) ?: throw UserNotFoundException()

        return AuthDetails(user)
    }
}