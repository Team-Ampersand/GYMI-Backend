package com.example.gymi.domain.auth.presentation.data.request

import javax.validation.constraints.NotBlank

data class SignInRequestDto(
        @field:NotBlank
        val code: String,
        val token: String?
)
