package com.example.gymi.domain.user.presentation

import com.example.gymi.domain.user.presentation.data.response.ReservationCourtNumberResponseDto
import com.example.gymi.domain.user.service.GetReservationCourtNumberService
import com.example.gymi.global.annotation.RequestController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestController("/user")
class UserController(
    private val getReservationCourtNumberService: GetReservationCourtNumberService
) {

    @GetMapping
    fun getCourtNumber(): ResponseEntity<ReservationCourtNumberResponseDto> =
        getReservationCourtNumberService.execute()
            .let { ResponseEntity.status(HttpStatus.OK).body(it) }
}