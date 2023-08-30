package com.example.gymi.domain.notice.presentation.data.dto

import com.example.gymi.domain.notice.entity.NoticeFile

data class NoticeFileDto(
    val id: Long,
    val url: String
) {
    constructor(noticeFile: NoticeFile) : this(
        id = noticeFile.id,
        url = noticeFile.url
    )
}
