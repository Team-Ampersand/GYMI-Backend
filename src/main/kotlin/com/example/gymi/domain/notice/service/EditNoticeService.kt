package com.example.gymi.domain.notice.service

import com.example.gymi.domain.notice.entity.Notice
import com.example.gymi.domain.notice.presentation.data.request.EditNoticeRequestDto
import org.springframework.web.multipart.MultipartFile

interface EditNoticeService {

    fun execute(id: Long, editNoticeRequestDto: EditNoticeRequestDto, multipartFile: List<MultipartFile>?): Notice
}