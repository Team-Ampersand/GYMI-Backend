package com.example.gymi.domain.reservation.repository

import com.example.gymi.domain.reservation.entity.Reservation
import com.example.gymi.domain.user.entity.User
import org.springframework.data.repository.CrudRepository

interface ReservationRepository : CrudRepository<Reservation, Long> {
    fun deleteByUser(user: User)

    fun findByUser(user: User): Reservation?
}