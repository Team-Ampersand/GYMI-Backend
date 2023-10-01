package com.example.gymi.domain.reservation.exception

import com.example.gymi.global.error.exception.BasicException
import com.example.gymi.global.error.exception.ErrorCode

class NotCourtReservationHourOrMinuteException : BasicException(ErrorCode.COURT_RESERVATION_HOUR_OR_MINUTE_IS_NOT_VALID) {
}