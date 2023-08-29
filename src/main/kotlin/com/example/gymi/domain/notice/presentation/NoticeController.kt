package com.example.gymi.domain.notice.presentation

import com.example.gymi.domain.notice.presentation.data.request.CreateNoticeRequestDto
import com.example.gymi.domain.notice.presentation.data.request.EditNoticeRequestDto
import com.example.gymi.domain.notice.presentation.data.response.ListNoticeResponseDto
import com.example.gymi.domain.notice.service.CreateNoticeService
import com.example.gymi.domain.notice.service.DeleteNoticeService
import com.example.gymi.domain.notice.service.EditNoticeService
import com.example.gymi.domain.notice.service.ListNoticeService
import com.example.gymi.global.annotation.RequestController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@RestController
@RequestController("/notice")
class NoticeController(
    private val createNoticeService: CreateNoticeService,
    private val deleteNoticeService: DeleteNoticeService,
    private val editNoticeService: EditNoticeService,
    private val listNoticeService: ListNoticeService
) {

    @PostMapping
    fun create(
        @RequestPart(value = "file", required = false) multipartFile: List<MultipartFile>?,
        @Valid @RequestPart(value = "notice") createNoticeRequestDto: CreateNoticeRequestDto
    ): ResponseEntity<Void> =
        createNoticeService.execute(createNoticeRequestDto, multipartFile)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        deleteNoticeService.execute(id)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @PatchMapping("/{id}")
    fun edit(
        @PathVariable id: Long,
        @Valid @RequestPart(value = "notice") editNoticeRequestDto: EditNoticeRequestDto,
        @RequestPart(value = "file", required = false) multipartFile: List<MultipartFile>?,
    ): ResponseEntity<Void> =
        editNoticeService.execute(id, editNoticeRequestDto, multipartFile)
            .let { ResponseEntity.status(HttpStatus.OK).build() }

    @GetMapping
    fun findList(): ResponseEntity<ListNoticeResponseDto> =
        listNoticeService.execute()
            .let{ ResponseEntity.status(HttpStatus.OK).body(it) }
}