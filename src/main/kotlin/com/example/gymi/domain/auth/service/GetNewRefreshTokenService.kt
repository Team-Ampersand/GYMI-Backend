package com.example.gymi.domain.auth.service

import com.example.gymi.domain.auth.presentation.data.dto.DeviceTokenDto
import com.example.gymi.domain.auth.presentation.data.response.NewRefreshTokenResponseDto

interface GetNewRefreshTokenService {
    fun execute(refreshToken: String, deviceTokenDto: DeviceTokenDto): NewRefreshTokenResponseDto
}