package com.example.gymi.domain.auth.presentation

import com.example.gymi.domain.auth.presentation.data.request.DeviceTokenRequest
import com.example.gymi.domain.auth.presentation.data.request.SignInRequestDto
import com.example.gymi.domain.auth.presentation.data.response.NewRefreshTokenResponseDto
import com.example.gymi.domain.auth.presentation.data.response.SignInResponseDto
import com.example.gymi.domain.auth.service.GetNewRefreshTokenService
import com.example.gymi.domain.auth.service.LogoutService
import com.example.gymi.domain.auth.service.SignInService
import com.example.gymi.domain.auth.util.AuthConverter
import com.example.gymi.global.annotation.RequestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestController("/auth")
class AuthController(
    private val signInService: SignInService,
    private val logoutService: LogoutService,
    private val authConverter: AuthConverter,
    private val getNewRefreshTokenService: GetNewRefreshTokenService
) {

    @PostMapping
    fun signIn(@RequestBody @Valid signInRequestDto: SignInRequestDto): ResponseEntity<SignInResponseDto> =
        authConverter.toDto(signInRequestDto)
            .let { ResponseEntity.ok(signInService.execute(it)) }

    @DeleteMapping
    fun logout(): ResponseEntity<Void> =
        logoutService.execute()
            .let { ResponseEntity.noContent().build() }

    @PatchMapping
    fun getNewRefreshToken(@RequestHeader("Refresh-Token") refreshToken: String, @RequestBody(required = false) deviceTokenRequest: DeviceTokenRequest?): ResponseEntity<NewRefreshTokenResponseDto> =
        authConverter.toDto(deviceTokenRequest?: DeviceTokenRequest(null))
            .let { ResponseEntity.ok(getNewRefreshTokenService.execute(refreshToken = refreshToken, it)) }
}