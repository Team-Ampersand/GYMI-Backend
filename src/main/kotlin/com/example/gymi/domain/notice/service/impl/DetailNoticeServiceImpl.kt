package com.example.gymi.domain.notice.service.impl

import com.example.gymi.domain.notice.exception.NoticeNotFoundException
import com.example.gymi.domain.notice.presentation.data.response.DetailNoticeResponseDto
import com.example.gymi.domain.notice.repository.NoticeRepository
import com.example.gymi.domain.notice.service.DetailNoticeService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DetailNoticeServiceImpl(
    private val noticeRepository: NoticeRepository
) : DetailNoticeService {
    override fun execute(id: Long): DetailNoticeResponseDto {
        val notice = noticeRepository.findByIdOrNull(id)
            ?: throw NoticeNotFoundException()

        return DetailNoticeResponseDto(notice)
    }
}