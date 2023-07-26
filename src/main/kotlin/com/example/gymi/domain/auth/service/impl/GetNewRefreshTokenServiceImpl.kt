package com.example.gymi.domain.auth.service.impl

import com.example.gymi.domain.auth.presentation.data.dto.DeviceTokenDto
import com.example.gymi.domain.auth.presentation.data.response.NewRefreshTokenResponseDto
import com.example.gymi.domain.auth.repository.RefreshTokenRepository
import com.example.gymi.domain.auth.service.GetNewRefreshTokenService
import com.example.gymi.domain.auth.util.AuthConverter
import com.example.gymi.domain.user.entity.DeviceToken
import com.example.gymi.domain.user.enums.Role
import com.example.gymi.domain.user.exception.UserNotFoundException
import com.example.gymi.domain.user.repository.DeviceTokenRepository
import com.example.gymi.domain.user.repository.UserRepository
import com.example.gymi.global.security.exception.TokenExpiredException
import com.example.gymi.global.security.exception.TokenInvalidException
import com.example.gymi.global.security.jwt.TokenProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
@Transactional(rollbackFor = [Exception::class])
class GetNewRefreshTokenServiceImpl(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val deviceTokenRepository: DeviceTokenRepository,
    private val userRepository: UserRepository,
    private val authConverter: AuthConverter,
    private val tokenProvider: TokenProvider
) : GetNewRefreshTokenService {

    override fun execute(refreshToken: String, deviceTokenDto: DeviceTokenDto): NewRefreshTokenResponseDto {
        val refresh = tokenProvider.parseToken(refreshToken)
            ?: throw TokenInvalidException()

        val email: String = tokenProvider.exactEmailFromRefreshToken(refresh)

        val role: Role = tokenProvider.exactRoleFromRefreshToken(refresh)

        val existingRefreshToken = refreshTokenRepository.findByToken(refresh)
            ?: throw TokenExpiredException()

        val newAccessToken = tokenProvider.generateAccessToken(email, role)
        val newRefreshToken = tokenProvider.generateRefreshToken(email, role)
        val accessExp: ZonedDateTime = tokenProvider.accessExpiredTime
        val refreshExp: ZonedDateTime = tokenProvider.refreshExpiredTime

        val newRefreshTokenEntity = authConverter.toEntity(
            userId = existingRefreshToken.userId,
            refreshToken = newRefreshToken
        )

        refreshTokenRepository.save(newRefreshTokenEntity)
        val user = userRepository.findByEmail(email)
            ?: throw UserNotFoundException()
        deviceTokenRepository.save(DeviceToken(user.id, user, deviceTokenDto.deviceToken?:""))

        return NewRefreshTokenResponseDto(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken,
            accessExp = accessExp,
            refreshExp = refreshExp
        )
    }
}