package com.example.gymi.domain.reservation.exception

import com.example.gymi.global.error.exception.BasicException
import com.example.gymi.global.error.exception.ErrorCode

class CourtReservationNotFoundException : BasicException(ErrorCode.COURT_RESERVATION_NOT_FOUND) {
}