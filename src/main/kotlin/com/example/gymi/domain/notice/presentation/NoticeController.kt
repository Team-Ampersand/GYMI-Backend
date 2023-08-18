package com.example.gymi.domain.notice.presentation

import com.example.gymi.domain.notice.presentation.data.request.CreateNoticeRequestDto
import com.example.gymi.domain.notice.service.CreateNoticeService
import com.example.gymi.global.annotation.RequestController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@RestController
@RequestController("/notice")
class NoticeController(
    private val createNoticeService: CreateNoticeService
) {

    @PostMapping
    fun create(
        @RequestPart(value = "file", required = false) multipartFile: List<MultipartFile>?,
        @Valid @RequestPart(value = "notice") createNoticeRequestDto: CreateNoticeRequestDto
    ): ResponseEntity<Void> =
        createNoticeService.execute(createNoticeRequestDto, multipartFile)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
}