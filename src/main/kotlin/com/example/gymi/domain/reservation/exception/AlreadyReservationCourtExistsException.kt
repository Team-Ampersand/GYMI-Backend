package com.example.gymi.domain.reservation.exception

import com.example.gymi.global.error.exception.BasicException
import com.example.gymi.global.error.exception.ErrorCode

class AlreadyReservationCourtExistsException : BasicException(ErrorCode.ALREADY_RESERVATION_COURT_IS_EXISTS) {
}