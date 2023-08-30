package com.example.gymi.domain.notice.presentation.data.response

import com.example.gymi.domain.notice.entity.Notice
import com.example.gymi.domain.notice.presentation.data.dto.NoticeFileDto
import com.example.gymi.domain.user.enums.Role
import java.time.LocalDateTime

data class DetailNoticeResponseDto(
    val id: Long,
    val title: String,
    val content: String,
    val role: Role,
    val noticeFile: List<NoticeFileDto>,
    val createdDate: LocalDateTime
) {
    constructor(notice: Notice) : this(
        id = notice.id,
        title = notice.title,
        content = notice.content,
        role = notice.user.roles[0],
        noticeFile = notice.noticeFile.map { NoticeFileDto(it) },
        createdDate = notice.createdDate
    )
}
