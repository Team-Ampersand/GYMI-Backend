package com.example.gymi.domain.reservation.repository

import com.example.gymi.domain.reservation.entity.Reservation
import org.springframework.data.repository.CrudRepository

interface ReservationRepository : CrudRepository<Reservation, Long> {
}