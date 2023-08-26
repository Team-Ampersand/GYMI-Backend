package com.example.gymi.domain.notice.service.impl

import com.example.gymi.domain.file.service.FileService
import com.example.gymi.domain.file.util.FileUtil
import com.example.gymi.domain.notice.entity.Notice
import com.example.gymi.domain.notice.entity.NoticeFile
import com.example.gymi.domain.notice.exception.NoticeNotFoundException
import com.example.gymi.domain.notice.presentation.data.dto.EditNoticeDto
import com.example.gymi.domain.notice.presentation.data.request.EditNoticeRequestDto
import com.example.gymi.domain.notice.repository.NoticeFileRepository
import com.example.gymi.domain.notice.repository.NoticeRepository
import com.example.gymi.domain.notice.service.EditNoticeService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
@Transactional(rollbackFor = [Exception::class])
class EditNoticeServiceImpl(
    private val noticeRepository: NoticeRepository,
    private val noticeFileRepository: NoticeFileRepository,
    private val fileService: FileService,
    private val fileUtil: FileUtil
) : EditNoticeService {

    override fun execute(id: Long, editNoticeRequestDto: EditNoticeRequestDto, multipartFile: List<MultipartFile>?): Notice {

        val noticeInfo: Notice = noticeRepository.findByIdOrNull(id)
            ?: throw NoticeNotFoundException()

        val noticeFileInfo: List<NoticeFile> = noticeFileRepository.findAllByNoticeId(noticeInfo.id)

        toDto(editNoticeRequestDto)
            .let { noticeInfo.editNotice(title = it.title, content = it.content) }

        if (multipartFile != null) {
            val editFileUrls: List<String> = fileService.execute(multipartFile)

            noticeFileInfo.zip(editFileUrls)
                .forEach { (noticeFile, fileUrl) ->
                    noticeFile.editNoticeFile(fileUrl)
                }

            editFileUrls.drop(noticeFileInfo.size)
                .map { newFileUrl -> toEntity(noticeInfo, newFileUrl) }
                .forEach { newNoticeFile ->
                    fileUtil.saveFile(newNoticeFile)
                }
        }

        return noticeInfo
    }

    private fun toDto(editNoticeRequestDto: EditNoticeRequestDto): EditNoticeDto =
        EditNoticeDto(
            title = editNoticeRequestDto.title,
            content = editNoticeRequestDto.content
        )

    private fun toEntity(notice: Notice, uploadFileUrl: String): NoticeFile =
        NoticeFile(
            notice = notice,
            url = uploadFileUrl
        )
}
