package com.example.gymi.domain.court.presentation

import com.example.gymi.domain.court.enum.CourtNumber
import com.example.gymi.domain.court.presentation.data.response.ReservationCourtInfoResponseDto
import com.example.gymi.domain.court.service.GetCourtInfoService
import com.example.gymi.global.annotation.RequestController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestController("/court")
class CourtController(
    private val getCourtInfoService: GetCourtInfoService
) {

    @GetMapping("/{courtNumber}")
    fun getCourtInfo(@PathVariable courtNumber: CourtNumber): ResponseEntity<ReservationCourtInfoResponseDto> =
        getCourtInfoService.execute(courtNumber)
            .let { ResponseEntity.status(HttpStatus.OK).body(it) }
}