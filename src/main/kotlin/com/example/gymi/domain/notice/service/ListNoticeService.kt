package com.example.gymi.domain.notice.service

import com.example.gymi.domain.notice.presentation.data.response.ListNoticeResponseDto

interface ListNoticeService {

    fun execute(): ListNoticeResponseDto
}