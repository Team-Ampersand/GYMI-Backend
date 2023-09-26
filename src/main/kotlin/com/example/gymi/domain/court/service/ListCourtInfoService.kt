package com.example.gymi.domain.court.service

import com.example.gymi.domain.court.presentation.data.response.ListReservationCourtInfoResponseDto

interface ListCourtInfoService {

    fun execute(): ListReservationCourtInfoResponseDto
}