package com.example.gymi.domain.court.presentation.data.response

import com.example.gymi.domain.court.entity.Court
import com.example.gymi.domain.court.enum.CourtNumber
import com.example.gymi.domain.court.enum.DayPeriod
import com.example.gymi.domain.court.enum.Week
import com.example.gymi.domain.court.presentation.data.dto.ReservationUsersInfoDto

data class ReservationCourtInfoResponseDto(
    val id: Long,
    val name: String,
    val count: Int,
    val maxCount: Int,
    val courtNumber: CourtNumber,
    val week: Week,
    val dayPeriod: DayPeriod,
    val reservations: List<ReservationUsersInfoDto>,
    val activity: String
) {
    constructor(court: Court) : this(
        id = court.id,
        name = court.name,
        count = court.count,
        maxCount = court.maxCount,
        courtNumber = court.courtNumber,
        week = court.week,
        dayPeriod = court.dayPeriod,
        reservations = court.reservations.map { ReservationUsersInfoDto(it.user) },
        activity = court.activity.name
    )
}
