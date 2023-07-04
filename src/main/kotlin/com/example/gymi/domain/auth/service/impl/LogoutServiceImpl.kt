package com.example.gymi.domain.auth.service.impl

import com.example.gymi.domain.auth.entity.RefreshToken
import com.example.gymi.domain.auth.repository.RefreshTokenRepository
import com.example.gymi.domain.auth.service.LogoutService
import com.example.gymi.domain.user.entity.User
import com.example.gymi.domain.user.exception.UserNotFoundException
import com.example.gymi.global.util.UserUtil
import org.springframework.stereotype.Service

@Service
class LogoutServiceImpl(
    private val userUtil: UserUtil,
    private val refreshTokenRepository: RefreshTokenRepository
) : LogoutService {

    override fun execute() {
        val user: User = userUtil.currentUser()

        val refreshToken: RefreshToken = refreshTokenRepository.findByUserId(user.id)
            ?: throw UserNotFoundException()

        refreshTokenRepository.delete(refreshToken)
    }
}