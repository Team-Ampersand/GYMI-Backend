package com.example.gymi.domain.court.schedule

import com.example.gymi.domain.court.service.UpdateCourtStatusService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class CourtSchedule(
    private val updateCourtStatusService: UpdateCourtStatusService
) {
    @Scheduled(cron = "0 0 11,17 ? * MON-FRI")
    fun courtReservationStatusReset() {
        updateCourtStatusService.execute()
    }
}