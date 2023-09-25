package com.example.gymi.domain.reservation.service

import com.example.gymi.domain.court.enum.CourtNumber

interface CancelCourtReservationService {

    fun execute(courtNumber: CourtNumber)
}