package com.example.gymi.domain.user.repository

import com.example.gymi.domain.user.entity.DeviceToken
import org.springframework.data.repository.CrudRepository
import java.util.*

interface DeviceTokenRepository: CrudRepository<DeviceToken, UUID> {
}