package com.example.gymi.domain.court.presentation.data.dto

import com.example.gymi.domain.user.entity.User
import java.util.UUID

data class ReservationUsersInfoDto(
    val id: UUID,
    val nickname: String,
    val grade: Int,
    val classNum: Int,
    val number: Int
) {
    constructor(user: User) : this(
        id = user.id,
        nickname = user.nickname,
        grade = user.grade,
        classNum = user.classNum,
        number = user.number
    )
}

