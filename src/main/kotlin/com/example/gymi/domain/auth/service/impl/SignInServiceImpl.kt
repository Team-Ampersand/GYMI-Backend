package com.example.gymi.domain.auth.service.impl

import com.example.gymi.domain.auth.exception.RoleNotExistException
import com.example.gymi.domain.auth.presentation.data.dto.SignInDto
import com.example.gymi.domain.auth.presentation.data.response.SignInResponseDto
import com.example.gymi.domain.auth.service.SignInService
import com.example.gymi.domain.auth.util.AuthUtil
import com.example.gymi.domain.user.enums.Role
import com.example.gymi.domain.user.repository.UserRepository
import com.example.gymi.global.gauth.properties.GAuthProperties
import com.example.gymi.global.security.jwt.TokenProvider
import gauth.GAuth
import gauth.GAuthToken
import gauth.GAuthUserInfo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Service
@Transactional(rollbackFor = [Exception::class])
class SignInServiceImpl(
    private val gAuthProperties: GAuthProperties,
    private val userRepository: UserRepository,
    private val tokenProvider: TokenProvider,
    private val gAuth: GAuth,
    private val authUtil: AuthUtil,
) : SignInService {


    override fun execute(signInDto: SignInDto): SignInResponseDto {
        val gAuthToken: GAuthToken = gAuth.generateToken(
            signInDto.code,
            gAuthProperties.clientId,
            gAuthProperties.clientSecret,
            gAuthProperties.redirectUri
        )
        val gAuthUserInfo: GAuthUserInfo = gAuth.getUserInfo(gAuthToken.accessToken)
        val role = getRoleByGauthInfo(gAuthUserInfo.role, gAuthUserInfo.email)
        val deviceToken = signInDto.deviceToken

        val accessToken: String = tokenProvider.generateAccessToken(gAuthUserInfo.email, role)
        val refreshToken: String = tokenProvider.generateRefreshToken(gAuthUserInfo.email, role)
        val accessExp: ZonedDateTime = tokenProvider.accessExpiredTime
        val refreshExp: ZonedDateTime = tokenProvider.refreshExpiredTime

        if(role == Role.ROLE_ADMIN) {
            createAdminOrRefreshToken(gAuthUserInfo, refreshToken, deviceToken)
        } else {
            createUserOrRefreshToken(gAuthUserInfo, refreshToken, deviceToken)
        }

        return SignInResponseDto(
            accessToken = accessToken,
            refreshToken = refreshToken,
            accessExp = accessExp,
            refreshExp = refreshExp
        )
    }

    private fun getRoleByGauthInfo(role: String, email: String): Role {
        val user = userRepository.findByEmail(email) ?:
        return when (role) {
            "ROLE_STUDENT" -> Role.ROLE_STUDENT
            "ROLE_TEACHER" -> Role.ROLE_ADMIN
            else -> throw RoleNotExistException()
        }
        if(user.roles.contains(Role.ROLE_ADMIN))
            return Role.ROLE_ADMIN
        return Role.ROLE_STUDENT
    }

    private fun createUserOrRefreshToken(gAuthUserInfo: GAuthUserInfo, refreshToken: String, deviceToken: String?) {
        val userInfo = userRepository.findByEmail(gAuthUserInfo.email)
        if (userInfo == null) {
            authUtil.saveNewUser(gAuthUserInfo, refreshToken, deviceToken)
        } else {
            authUtil.saveNewRefreshToken(userInfo, refreshToken, deviceToken)
        }
    }

    private fun createAdminOrRefreshToken(gAuthUserInfo: GAuthUserInfo, refreshToken: String, deviceToken: String?) {
        val adminInfo = userRepository.findByEmail(gAuthUserInfo.email)
        if (adminInfo == null) {
            authUtil.saveNewAdmin(gAuthUserInfo, refreshToken, deviceToken)
        } else {
            authUtil.saveNewRefreshToken(adminInfo, refreshToken, deviceToken)
        }
    }

}