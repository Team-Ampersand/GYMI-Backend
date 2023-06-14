package com.example.gymi.domain.auth.presentation.data.request

data class SignInRequestDto(
        val code: String,
        val token: String?
)
