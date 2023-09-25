package com.example.gymi.domain.reservation.util

import com.example.gymi.domain.court.entity.Court
import com.example.gymi.domain.court.enum.CourtNumber
import com.example.gymi.domain.court.repository.CourtRepository
import org.springframework.stereotype.Component

@Component
class FindReservationCountUtil(
    private val courtRepository: CourtRepository
) {
    fun findReservationCount(courtNumber: CourtNumber): Court =
        courtRepository.findCourtByCourtNumber(courtNumber)
}