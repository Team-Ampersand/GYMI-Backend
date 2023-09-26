package com.example.gymi.domain.court.service

import com.example.gymi.domain.court.enum.CourtNumber
import com.example.gymi.domain.court.presentation.data.response.ReservationCourtInfoResponseDto

interface GetCourtInfoService {

    fun execute(courtNumber: CourtNumber): ReservationCourtInfoResponseDto
}