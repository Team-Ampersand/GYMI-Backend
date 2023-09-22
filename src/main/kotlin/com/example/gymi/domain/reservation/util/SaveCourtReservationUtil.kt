package com.example.gymi.domain.reservation.util

import com.example.gymi.domain.court.entity.Court
import com.example.gymi.domain.reservation.entity.Reservation
import com.example.gymi.domain.reservation.repository.ReservationRepository
import com.example.gymi.domain.user.entity.User
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class SaveCourtReservationUtil(
    private val reservationRepository: ReservationRepository
) {
    fun saveCourtReservation(court: Court, user: User) {
        reservationRepository.save(
            Reservation(
            dateTime = ZonedDateTime.now(),
            court = court,
            user = user
        ))
    }
}