package com.example.gymi.domain.reservation.presentation

import com.example.gymi.domain.court.enum.CourtNumber
import com.example.gymi.domain.reservation.service.CancelCourtReservationService
import com.example.gymi.domain.reservation.service.CourtReservationService
import com.example.gymi.global.annotation.RequestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@RequestController("/reservation")
class ReservationController(
    private val courtReservationService: CourtReservationService,
    private val cancelCourtReservationService: CancelCourtReservationService
) {

    @PostMapping
    fun courtReservation(@RequestParam courtNumber: CourtNumber): ResponseEntity<Void> =
        courtReservationService.execute(courtNumber)
            .let { ResponseEntity.ok().build() }

    @DeleteMapping
    fun cancelCourtReservation(@RequestParam courtNumber: CourtNumber): ResponseEntity<Void> =
        cancelCourtReservationService.execute(courtNumber)
            .let { ResponseEntity.ok().build() }
}