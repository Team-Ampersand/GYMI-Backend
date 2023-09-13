package com.example.gymi.domain.reservation.entity

import com.example.gymi.domain.court.entity.Court
import com.example.gymi.domain.reservation.enum.CourtNumber
import com.example.gymi.domain.user.entity.User
import javax.persistence.*

@Entity
class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val courtNumber: CourtNumber,

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "court_id")
    val court: Court,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
)