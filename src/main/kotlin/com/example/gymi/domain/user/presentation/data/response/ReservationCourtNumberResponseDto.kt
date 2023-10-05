package com.example.gymi.domain.user.presentation.data.response

import com.example.gymi.domain.court.enum.CourtNumber
import com.example.gymi.domain.reservation.entity.Reservation

data class ReservationCourtNumberResponseDto(
    val courtNumber: CourtNumber
) {
    constructor(reservation: Reservation) : this (
        courtNumber = reservation.court.courtNumber
    )
}
