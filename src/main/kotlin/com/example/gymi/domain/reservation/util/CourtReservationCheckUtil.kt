package com.example.gymi.domain.reservation.util

import com.example.gymi.domain.court.enum.CourtNumber
import com.example.gymi.domain.reservation.exception.CourtReservationCancelException
import com.example.gymi.domain.user.entity.User
import com.example.gymi.domain.user.enums.ReservationStatus
import org.springframework.stereotype.Component

@Component
class CourtReservationCheckUtil(
    private val findReservationCountUtil: FindReservationCountUtil
) {
    fun reservationCheck(courtNumber: CourtNumber, user: User) {

        val court = findReservationCountUtil.findReservationCount(courtNumber)

        val userHasReservation = court.reservations.any { it.user == user }

        if (!userHasReservation && user.reservationStatus != ReservationStatus.APPLIED) {
            throw CourtReservationCancelException()
        } else {
            court.removeCount()
        }
    }
}