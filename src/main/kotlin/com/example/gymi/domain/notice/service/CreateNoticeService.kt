package com.example.gymi.domain.notice.service

import com.example.gymi.domain.notice.entity.Notice
import com.example.gymi.domain.notice.presentation.data.request.CreateNoticeRequestDto
import org.springframework.web.multipart.MultipartFile

interface CreateNoticeService {

    fun execute(createNoticeRequestDto: CreateNoticeRequestDto, multipartFile: List<MultipartFile>?): Notice
}