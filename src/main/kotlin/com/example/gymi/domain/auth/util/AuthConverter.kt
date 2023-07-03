package com.example.gymi.domain.auth.util

import com.example.gymi.domain.auth.entity.RefreshToken
import com.example.gymi.domain.auth.presentation.data.dto.DeviceTokenDto
import com.example.gymi.domain.auth.presentation.data.dto.SignInDto
import com.example.gymi.domain.auth.presentation.data.request.DeviceTokenRequest
import com.example.gymi.domain.auth.presentation.data.request.SignInRequestDto
import com.example.gymi.domain.user.entity.User
import gauth.GAuthUserInfo
import java.util.*

interface AuthConverter {

    fun toDto(signInRequestDto: SignInRequestDto): SignInDto

    fun toDto(deviceTokenRequest: DeviceTokenRequest): DeviceTokenDto

    fun toEntity(gAuthUserInfo: GAuthUserInfo): User

    fun toAdminEntity(gAuthUserInfo: GAuthUserInfo): User

    fun toEntity(userInfo: User, refreshToken: String): RefreshToken

    fun toEntity(userId: UUID?, refreshToken: String): RefreshToken

}