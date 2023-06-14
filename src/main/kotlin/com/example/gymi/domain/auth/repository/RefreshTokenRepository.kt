package com.example.gymi.domain.auth.repository

import com.example.gymi.domain.auth.entity.RefreshToken
import org.springframework.data.repository.CrudRepository
import java.util.*

interface RefreshTokenRepository : CrudRepository<RefreshToken, UUID> {
}