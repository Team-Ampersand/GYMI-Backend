package com.example.gymi.domain.user.repository

import com.example.gymi.domain.user.entity.User
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface UserRepository : CrudRepository<User, UUID> {
    fun findByEmail(email: String): User?
}