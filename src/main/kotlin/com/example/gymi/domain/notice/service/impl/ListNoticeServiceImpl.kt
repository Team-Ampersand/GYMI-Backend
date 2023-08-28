package com.example.gymi.domain.notice.service.impl

import com.example.gymi.domain.notice.presentation.data.response.ListNoticeResponseDto
import com.example.gymi.domain.notice.presentation.data.response.NoticeResponseDto
import com.example.gymi.domain.notice.repository.NoticeRepository
import com.example.gymi.domain.notice.service.ListNoticeService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ListNoticeServiceImpl(
    private val noticeRepository: NoticeRepository
) : ListNoticeService {

    override fun execute(): ListNoticeResponseDto = ListNoticeResponseDto(
        noticeList = noticeRepository.findAllByOrderByCreatedDateDesc()
            .map { NoticeResponseDto.of(it) }
    )
}