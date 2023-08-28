package com.example.gymi.domain.notice.presentation.data.response

import com.example.gymi.domain.notice.entity.Notice
import com.example.gymi.domain.user.enums.Role
import java.time.LocalDateTime

data class NoticeResponseDto(
    val id: Long,
    val title: String,
    val content: String,
    val role: Role,
    val createdDate: LocalDateTime
) {
    companion object {
        fun of(notice: Notice) = NoticeResponseDto(
            id = notice.id,
            title = notice.title,
            content = notice.content,
            role = notice.user.roles[0],
            createdDate = notice.createdDate
        )
    }
}