package com.example.gymi.domain.reservation.exception

import com.example.gymi.global.error.exception.BasicException
import com.example.gymi.global.error.exception.ErrorCode

class NotCourtReservationCancelDayException : BasicException(ErrorCode.COURT_RESERVATION_CANCEL_DAY_IS_NOT_VALID) {
}