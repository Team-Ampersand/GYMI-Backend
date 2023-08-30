package com.example.gymi.domain.notice.service

import com.example.gymi.domain.notice.presentation.data.response.DetailNoticeResponseDto

interface DetailNoticeService {

    fun execute(id: Long): DetailNoticeResponseDto
}