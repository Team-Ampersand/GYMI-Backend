package com.example.gymi.domain.auth.util.impl

import com.example.gymi.domain.auth.entity.RefreshToken
import com.example.gymi.domain.auth.presentation.data.dto.DeviceTokenDto
import com.example.gymi.domain.auth.presentation.data.dto.SignInDto
import com.example.gymi.domain.auth.presentation.data.request.DeviceTokenRequest
import com.example.gymi.domain.auth.presentation.data.request.SignInRequestDto
import com.example.gymi.domain.auth.util.AuthConverter
import com.example.gymi.domain.user.entity.User
import com.example.gymi.domain.user.enums.Role
import gauth.GAuthUserInfo
import org.springframework.stereotype.Component
import java.util.*

@Component
class AuthConverterImpl : AuthConverter {
    override fun toDto(signInRequestDto: SignInRequestDto): SignInDto =
        SignInDto(
                code = signInRequestDto.code,
                token = signInRequestDto.token
        )

    override fun toDto(deviceTokenRequest: DeviceTokenRequest): DeviceTokenDto =
        DeviceTokenDto(deviceTokenRequest.token)


    override fun toEntity(gAuthUserInfo: GAuthUserInfo): User =
        User(
            id = UUID.randomUUID(),
            email = gAuthUserInfo.email,
            nickname = gAuthUserInfo.name,
            grade = gAuthUserInfo.grade,
            classNum = gAuthUserInfo.classNum,
            number = gAuthUserInfo.num,
            roles = mutableListOf(Role.ROLE_STUDENT)
        )

    override fun toAdminEntity(gAuthUserInfo: GAuthUserInfo): User =
        User(
            id = UUID.randomUUID(),
            email = gAuthUserInfo.email,
            nickname = gAuthUserInfo.name,
            grade = 0,
            classNum = 0,
            number = 0,
            roles = mutableListOf(Role.ROLE_ADMIN)
        )

    override fun toEntity(userInfo: User, refreshToken: String): RefreshToken =
        RefreshToken(
            userId = userInfo.id,
            token = refreshToken
        )


    override fun toEntity(userId: UUID?, refreshToken: String): RefreshToken =
        RefreshToken(
            userId = userId,
            token = refreshToken
        )
}