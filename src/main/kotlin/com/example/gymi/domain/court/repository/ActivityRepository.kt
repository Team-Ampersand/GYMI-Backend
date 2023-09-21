package com.example.gymi.domain.court.repository

import com.example.gymi.domain.court.entity.Activity
import org.springframework.data.jpa.repository.JpaRepository

interface ActivityRepository : JpaRepository<Activity, Long>{
}