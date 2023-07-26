package com.example.gymi.domain.auth.presentation.data.dto

data class SignInDto(
    val code: String,
    val deviceToken: String?
)