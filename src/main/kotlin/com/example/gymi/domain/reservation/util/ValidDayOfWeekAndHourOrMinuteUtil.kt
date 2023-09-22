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
    private val currentTime: LocalDateTime? = null
) {

    fun validateApply() {
        val currentTime = currentTime ?: LocalDateTime.now()
        val dayOfWeek = currentTime.dayOfWeek
        val hour = currentTime.hour
        val minute = currentTime.minute

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)
            throw NotCourtReservationDayException()

        if (!((hour == 11 && minute >= 30) || (hour == 17 && minute >= 30))) {
            throw NotCourtReservationHourOrMinuteException()
        }
    }

    fun validateCancel() {
        val currentTime = currentTime ?: LocalDateTime.now()
        val dayOfWeek = currentTime.dayOfWeek
        val hour = currentTime.hour

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)
            throw NotCourtReservationCancelDayException()

        if (hour != 11 && hour != 17)
            throw NotCourtReservationCancelHourOrMinuteException()
    }
}