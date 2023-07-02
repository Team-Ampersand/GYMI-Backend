package com.example.gymi.domain.auth.presentation

import com.example.gymi.domain.auth.presentation.data.request.SignInRequestDto
import com.example.gymi.domain.auth.presentation.data.response.SignInResponseDto
import com.example.gymi.domain.auth.service.SignInService
import com.example.gymi.domain.auth.util.AuthConverter
import com.example.gymi.global.annotation.RequestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid

@RequestController("/auth")
class AuthController(
        private val signInService: SignInService,
        private val authConverter: AuthConverter
) {

    @PostMapping
    fun signIn(@RequestBody @Valid signInRequestDto: SignInRequestDto): ResponseEntity<SignInResponseDto> =
            authConverter.toDto(signInRequestDto)
                    .let { ResponseEntity.ok(signInService.execute(it)) }
}