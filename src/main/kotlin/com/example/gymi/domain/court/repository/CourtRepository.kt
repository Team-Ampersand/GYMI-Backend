package com.example.gymi.domain.court.repository

import com.example.gymi.domain.court.entity.Court
import com.example.gymi.domain.court.enum.CourtNumber
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.repository.CrudRepository
import javax.persistence.LockModeType

interface CourtRepository : CrudRepository<Court, Long> {

    fun findByCourtNumber(courtNumber: CourtNumber): Court

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findCourtByIdAndCourtNumber(id: Long, courtNumber: CourtNumber): Court
}