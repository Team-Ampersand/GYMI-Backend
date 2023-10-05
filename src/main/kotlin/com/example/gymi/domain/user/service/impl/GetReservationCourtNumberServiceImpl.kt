package com.example.gymi.domain.user.service.impl

import com.example.gymi.domain.reservation.exception.CourtReservationNotFoundException
import com.example.gymi.domain.reservation.repository.ReservationRepository
import com.example.gymi.domain.user.presentation.data.response.ReservationCourtNumberResponseDto
import com.example.gymi.domain.user.service.GetReservationCourtNumberService
import com.example.gymi.global.util.UserUtil
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class GetReservationCourtNumberServiceImpl(
    private val userUtil: UserUtil,
    private val reservationRepository: ReservationRepository
) : GetReservationCourtNumberService {

    override fun execute(): ReservationCourtNumberResponseDto {

        val user = userUtil.currentUser()

        val reservation = reservationRepository.findByUser(user) ?: throw CourtReservationNotFoundException()

        return ReservationCourtNumberResponseDto(reservation)
    }
}
