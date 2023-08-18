package com.example.gymi.domain.notice.util

import com.example.gymi.domain.notice.entity.Notice
import com.example.gymi.domain.notice.repository.NoticeRepository
import org.springframework.stereotype.Component

@Component
class NoticeUtil(

    private val noticeRepository: NoticeRepository
) {

    fun saveNotice(notice: Notice) =
        noticeRepository.save(notice)
}