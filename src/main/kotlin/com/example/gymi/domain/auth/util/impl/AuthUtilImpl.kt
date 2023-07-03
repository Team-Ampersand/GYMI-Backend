package com.example.gymi.domain.auth.util.impl

import com.example.gymi.domain.auth.entity.RefreshToken
import com.example.gymi.domain.auth.repository.RefreshTokenRepository
import com.example.gymi.domain.auth.util.AuthConverter
import com.example.gymi.domain.auth.util.AuthUtil
import com.example.gymi.domain.user.entity.DeviceToken
import com.example.gymi.domain.user.entity.User
import com.example.gymi.domain.user.repository.DeviceTokenRepository
import com.example.gymi.domain.user.repository.UserRepository
import gauth.GAuthUserInfo
import org.springframework.stereotype.Component

@Component
class AuthUtilImpl(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val authConverter: AuthConverter,
    private val userRepository: UserRepository,
    private val deviceTokenRepository: DeviceTokenRepository
) : AuthUtil {
    override fun saveNewUser(gAuthUserInfo: GAuthUserInfo, refreshToken: String, token: String?) {
        val signInUserInfo: User = authConverter.toEntity(gAuthUserInfo)
            .let { userRepository.save(it) }
        saveNewRefreshToken(signInUserInfo, refreshToken, token)
    }

    override fun saveNewAdmin(gAuthUserInfo: GAuthUserInfo, refreshToken: String, token: String?) {
        val signInAdminInfo: User = authConverter.toAdminEntity(gAuthUserInfo)
            .let { userRepository.save(it) }
        saveNewRefreshToken(signInAdminInfo, refreshToken, token)
    }

    override fun saveNewRefreshToken(userInfo: User, refreshToken: String, token: String?): RefreshToken {
        deviceTokenRepository.save(DeviceToken(userInfo.id, userInfo, token ?: ""))
        return authConverter.toEntity(userInfo, refreshToken)
            .let { refreshTokenRepository.save(it) }
    }
}