package com.example.gymi.domain.court.repository

import com.example.gymi.domain.court.entity.Court
import com.example.gymi.domain.court.enum.CourtNumber
import org.springframework.data.repository.CrudRepository

interface CourtRepository : CrudRepository<Court, Long> {

    fun findByCourtNumber(courtNumber: CourtNumber): Court
}