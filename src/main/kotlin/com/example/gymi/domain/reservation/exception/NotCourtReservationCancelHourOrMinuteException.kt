package com.example.gymi.domain.reservation.exception

import com.example.gymi.global.error.exception.BasicException
import com.example.gymi.global.error.exception.ErrorCode

class NotCourtReservationCancelHourOrMinuteException : BasicException(ErrorCode.COURT_RESERVATION_CANCEL_HOUR_OR_MINUTE_IS_NOT_VALID) {
}