package com.example.gymi.domain.court.service.impl

import com.example.gymi.domain.court.entity.Activity
import com.example.gymi.domain.court.entity.Court
import com.example.gymi.domain.court.enum.CourtNumber
import com.example.gymi.domain.court.enum.DayPeriod
import com.example.gymi.domain.court.enum.Week
import com.example.gymi.domain.court.repository.ActivityRepository
import com.example.gymi.domain.court.repository.CourtRepository
import com.example.gymi.domain.court.service.UpdateCourtStatusService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.DayOfWeek
import java.time.LocalDateTime

@Service
@Transactional(rollbackFor = [Exception::class])
class UpdateCourtStatusServiceImpl(
    private val courtRepository: CourtRepository,
    private val activityRepository: ActivityRepository
) : UpdateCourtStatusService {

    private val courtNumbers = CourtNumber.values()

    override fun execute() {
        val currentDateTime = LocalDateTime.now()
        val currentHour = currentDateTime.hour
        val currentDayOfWeek = currentDateTime.dayOfWeek
        val courtMaxCount = calculateMaxCourtCount(currentDayOfWeek)

        courtRepository.deleteAll()

        if (currentDayOfWeek == DayOfWeek.FRIDAY) {
            fridayCourtEntitySetting(currentHour)
        } else {
            courtEntitySetting(currentDayOfWeek, courtMaxCount, currentHour)
        }
    }

    private fun calculateMaxCourtCount(dayOfWeek: DayOfWeek): Int {
        return when (dayOfWeek) {
            DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY -> 10
            DayOfWeek.TUESDAY, DayOfWeek.THURSDAY -> 4
            else -> 0
        }
    }

    private fun courtEntitySetting(
        currentDayOfWeek: DayOfWeek,
        courtMaxCount: Int,
        currentHour: Int
    ) {
        for ((index, courtNumber) in courtNumbers.withIndex()) {

            val activityText = when (currentDayOfWeek) {
                DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY -> "농구"
                DayOfWeek.TUESDAY, DayOfWeek.THURSDAY -> "배드민턴"
                else -> ""
            }

            courtRepository.save(
                Court(
                    id = index + 1L,
                    name = "체육관 코트",
                    maxCount = courtMaxCount,
                    courtNumber = courtNumber,
                    week = convertToWeek(currentDayOfWeek),
                    dayPeriod = determineDayPeriod(currentHour),
                    reservations = listOf(),
                    activity = Activity(index + 1L, activityText)
                        .let { activityRepository.save(it) }
                )
            )
        }
    }

    private fun convertToWeek(dayOfWeek: DayOfWeek): Week {
        return when (dayOfWeek) {
            DayOfWeek.MONDAY -> Week.MONDAY
            DayOfWeek.TUESDAY -> Week.TUESDAY
            DayOfWeek.WEDNESDAY -> Week.WEDNESDAY
            DayOfWeek.THURSDAY -> Week.THURSDAY
            DayOfWeek.FRIDAY -> Week.FRIDAY
            else -> Week.MONDAY
        }
    }

    private fun determineDayPeriod(currentHour: Int): DayPeriod {
        return when {
            currentHour > 12 -> DayPeriod.LUNCH
            currentHour > 18 -> DayPeriod.EVENING
            else -> DayPeriod.MORNING
        }
    }

    private fun fridayCourtEntitySetting(currentHour: Int) {
        for ((index, courtNumber) in courtNumbers.withIndex()) {

            courtRepository.save(
                Court(
                    id = index + 1L,
                    name = "체육관 코트",
                    maxCount = if (index < 2) 10 else 4,
                    courtNumber = courtNumber,
                    week = Week.FRIDAY,
                    dayPeriod = determineDayPeriod(currentHour),
                    reservations = listOf(),
                    activity = Activity(index + 1L, "배드민턴, 농구")
                        .let { activityRepository.save(it) }
                )
            )
        }
    }
}