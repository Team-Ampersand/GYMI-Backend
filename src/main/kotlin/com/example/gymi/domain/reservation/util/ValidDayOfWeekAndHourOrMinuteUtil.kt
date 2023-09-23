package com.example.gymi.domain.reservation.util

import com.example.gymi.domain.reservation.exception.NotCourtReservationCancelDayException
import com.example.gymi.domain.reservation.exception.NotCourtReservationCancelHourOrMinuteException
import com.example.gymi.domain.reservation.exception.NotCourtReservationDayException
import com.example.gymi.domain.reservation.exception.NotCourtReservationHourOrMinuteException
import org.springframework.stereotype.Component
import java.time.DayOfWeek
import java.time.LocalDateTime

@Component
class ValidDayOfWeekAndHourOrMinuteUtil(
    private val currentTime: LocalDateTime? = null,
    private val COURT_RESERVATION_HOURS: Set<Int> = setOf(11, 17),
    private val COURT_RESERVATION_MINUTE: Int = 30
) {

    fun validateApply() {
        val currentTime = currentTime ?: LocalDateTime.now()
        val dayOfWeek = currentTime.dayOfWeek
        val hour = currentTime.hour
        val minute = currentTime.minute

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)
            throw NotCourtReservationDayException()

        if(!(COURT_RESERVATION_HOURS.contains(hour) && minute >= COURT_RESERVATION_MINUTE)) {
            throw NotCourtReservationHourOrMinuteException()
        }
    }

    fun validateCancel() {
        val currentTime = currentTime ?: LocalDateTime.now()
        val dayOfWeek = currentTime.dayOfWeek
        val hour = currentTime.hour

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)
            throw NotCourtReservationCancelDayException()

        if (!(COURT_RESERVATION_HOURS.contains(hour))) {
            throw NotCourtReservationCancelHourOrMinuteException()
        }
    }
}