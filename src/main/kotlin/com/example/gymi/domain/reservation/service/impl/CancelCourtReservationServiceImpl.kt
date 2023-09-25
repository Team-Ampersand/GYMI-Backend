package com.example.gymi.domain.reservation.service.impl

import com.example.gymi.domain.court.enum.CourtNumber
import com.example.gymi.domain.reservation.repository.ReservationRepository
import com.example.gymi.domain.reservation.service.CancelCourtReservationService
import com.example.gymi.domain.reservation.util.CourtReservationCheckUtil
import com.example.gymi.domain.reservation.util.ValidDayOfWeekAndHourOrMinuteUtil
import com.example.gymi.domain.user.enums.ReservationStatus
import com.example.gymi.global.util.UserUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class CancelCourtReservationServiceImpl(
    private val userUtil: UserUtil,
    private val reservationRepository: ReservationRepository,
    private val courtReservationCheckUtil: CourtReservationCheckUtil,
    private val validDayOfWeekAndHourOrMinuteUtil: ValidDayOfWeekAndHourOrMinuteUtil
) : CancelCourtReservationService {

    override fun execute(courtNumber: CourtNumber) {

        validDayOfWeekAndHourOrMinuteUtil.validateCancel()

        val user = userUtil.currentUser()

        courtReservationCheckUtil.reservationCheck(courtNumber, user)

        user.updateReservationStatus(ReservationStatus.CAN)

        reservationRepository.deleteByUser(user)
    }
}