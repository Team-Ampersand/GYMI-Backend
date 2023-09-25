package com.example.gymi.domain.reservation.exception

import com.example.gymi.global.error.exception.BasicException
import com.example.gymi.global.error.exception.ErrorCode

class CourtReservationCancelException : BasicException(ErrorCode.COURT_RESERVATION_CANT_CANCEL) {
}