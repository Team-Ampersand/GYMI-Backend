package com.example.gymi.domain.court.service.impl

import com.example.gymi.domain.court.presentation.data.response.ListReservationCourtInfoResponseDto
import com.example.gymi.domain.court.presentation.data.response.ReservationCourtInfoResponseDto
import com.example.gymi.domain.court.repository.CourtRepository
import com.example.gymi.domain.court.service.ListCourtInfoService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class ListCourtInfoServiceImpl(
    private val courtRepository: CourtRepository
) : ListCourtInfoService {
    override fun execute(): ListReservationCourtInfoResponseDto  = ListReservationCourtInfoResponseDto(
        courtList = courtRepository.findAll()
            .map { ReservationCourtInfoResponseDto(it) }
    )
}