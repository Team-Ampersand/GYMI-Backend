package com.example.gymi.domain.court.entity

import com.example.gymi.domain.court.enum.Week
import javax.persistence.*

@Entity
data class Court(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(length = 30)
    val name: String,

    @Column(length = 5)
    val maxCount: Int,

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    val week: Week
)