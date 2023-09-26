package com.example.gymi.domain.reservation.util

import com.example.gymi.domain.court.entity.Court
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

        if (isReservationNotAppliedAndNoReservation(courtNumber, user, court)) {
            throw CourtReservationCancelException()
        } else {
            court.removeCount()
        }
    }

    fun isReservationNotAppliedAndNoReservation(courtNumber: CourtNumber, user: User, court: Court): Boolean {

        val userHasReservation = court.reservations.any { it.user == user }

        return user.reservationStatus != ReservationStatus.APPLIED && !userHasReservation
    }
}