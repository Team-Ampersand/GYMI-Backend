package com.example.gymi.domain.court.service.impl

import com.example.gymi.domain.court.enum.CourtNumber
import com.example.gymi.domain.court.presentation.data.response.ReservationCourtInfoResponseDto
import com.example.gymi.domain.court.service.GetCourtInfoService
import com.example.gymi.domain.reservation.util.FindReservationCourtUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetCourtInfoServiceImpl(
    private val findReservationCourtUtil: FindReservationCourtUtil
) : GetCourtInfoService {
    override fun execute(courtNumber: CourtNumber): ReservationCourtInfoResponseDto {
        val court = findReservationCourtUtil.findReservationCount(courtNumber)

        return ReservationCourtInfoResponseDto(court)
    }

}