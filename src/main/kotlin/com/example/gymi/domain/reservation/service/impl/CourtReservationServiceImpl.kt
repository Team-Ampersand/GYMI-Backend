package com.example.gymi.domain.reservation.service.impl

import com.example.gymi.domain.court.enum.CourtNumber
import com.example.gymi.domain.reservation.exception.AlreadyReservationCourtExistsException
import com.example.gymi.domain.reservation.exception.CourtReservationOverException
import com.example.gymi.domain.reservation.service.CourtReservationService
import com.example.gymi.domain.reservation.util.FindReservationCourtUtil
import com.example.gymi.domain.reservation.util.SaveCourtReservationUtil
import com.example.gymi.domain.reservation.util.ValidDayOfWeekAndHourOrMinuteUtil
import com.example.gymi.domain.user.enums.ReservationStatus
import com.example.gymi.global.util.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class CourtReservationServiceImpl(
    private val userUtil: UserUtil,
    private val findReservationCourtUtil: FindReservationCourtUtil,
    private val saveCourtReservationUtil: SaveCourtReservationUtil,
    private val validDayOfWeekAndHourOrMinuteUtil: ValidDayOfWeekAndHourOrMinuteUtil
) : CourtReservationService {
    override fun execute(courtNumber: CourtNumber) {

        validDayOfWeekAndHourOrMinuteUtil.validateApply()

        val user = userUtil.currentUser()

        val court = findReservationCourtUtil.findReservationCount(courtNumber)

        if (court.count >= court.maxCount) {
            throw CourtReservationOverException()
        }

        if (user.reservationStatus == ReservationStatus.APPLIED) {
            throw AlreadyReservationCourtExistsException()
        }

        court.addCount()

        user.updateReservationStatus(ReservationStatus.APPLIED)

        saveCourtReservationUtil.saveCourtReservation(court, user)
    }
}