package com.example.gymi.domain.user.service

import com.example.gymi.domain.user.presentation.data.response.ReservationCourtNumberResponseDto

interface GetReservationCourtNumberService {

    fun execute(): ReservationCourtNumberResponseDto
}