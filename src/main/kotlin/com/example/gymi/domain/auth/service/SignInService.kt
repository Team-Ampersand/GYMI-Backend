package com.example.gymi.domain.auth.service

import com.example.gymi.domain.auth.presentation.data.dto.SignInDto
import com.example.gymi.domain.auth.presentation.data.response.SignInResponseDto

interface SignInService {
    fun execute(signInDto: SignInDto): SignInResponseDto
}