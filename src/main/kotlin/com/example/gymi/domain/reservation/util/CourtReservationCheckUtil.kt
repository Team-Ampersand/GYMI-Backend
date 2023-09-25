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

        if (isReservationNotAppliedAndNoReservation(courtNumber, user)) {
            throw CourtReservationCancelException()
        }
    }

    fun isReservationNotAppliedAndNoReservation(courtNumber: CourtNumber, user: User): Boolean {

        val court = findReservationCountUtil.findReservationCount(courtNumber)

        val userHasReservation = court.reservations.any { it.user == user }

        return user.reservationStatus != ReservationStatus.APPLIED && !userHasReservation
    }
}